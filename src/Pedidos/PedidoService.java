package Pedidos;

import Produtos.Produto;

public class PedidoService {

    private INotificadorPedido notificadorPedido;

    public PedidoService(INotificadorPedido notificadorPedido) {
        this.notificadorPedido = notificadorPedido;
    }

    public void finalizarPedido(Pedido pedido) {
        if (pedido.getTotal() > 0 && !pedido.getItens().isEmpty()) {
            pedido.setStatusPedido(StatusPedido.AGUARDANDO_PAGAMENTO);
            notificadorPedido.notificadorPedido("Pedido " + pedido.getId() + " aguardando pagamento", pedido.getCliente().getEmail());
        } else {
            System.out.println("Pedido só poderá ser finalizado quando tiver produto com valor.");
        }
    }

    public void pagarPedido(Pedido pedido) {
        if (pedido.getStatusPedido() == StatusPedido.AGUARDANDO_PAGAMENTO) {
            pedido.setStatusPedido(StatusPedido.PAGO);
            notificadorPedido.notificadorPedido("Pagamento do pedido " + pedido.getId(), pedido.getCliente().getEmail());
            System.out.println("Pagamento confirmado.");
        } else {
            System.out.println("O pedido não está aguardando nenhum pagamento.");
        }
    }

    public void entregarPedido(Pedido pedido) {
        if (pedido.getStatusPedido() == StatusPedido.PAGO) {
            pedido.setStatusPedido(StatusPedido.FINALIZADO);
            notificadorPedido.notificadorPedido("Pedido " + pedido.getId() + " foi entregue.", pedido.getCliente().getEmail());
        } else {
            System.out.println("Infelizmente o pagamento não foi realizado.");
        }
    }

    public static void adicionarItem(Pedido pedido, Produto produto, int quantidade, double valor) {
        pedido.adicionarItem(produto, quantidade, valor);
        System.out.println("Produto " + produto.getNome() + " adicionado ao pedido " + pedido.getId());
    }

    public void removerItem(Pedido pedido, ItemPedido itemPedido) {
        pedido.removerItem(itemPedido);
        System.out.println("Produto " + itemPedido.getProduto().getNome() + " removido do pedido " + pedido.getId());
    }

    public void alterarQuantidadeItem(Pedido pedido, ItemPedido itemPedido, int novaQuantidade) {
        pedido.alterarQuantidadeItem(itemPedido, novaQuantidade);
        System.out.println("Quantidade do produto " + itemPedido.getProduto().getNome() + " alterada para " + novaQuantidade + " no pedido " + pedido.getId());
    }
}
