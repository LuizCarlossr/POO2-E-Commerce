package Pedidos;

import Clientes.Cliente;
import Clientes.Notificador;
import Produtos.Produto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
    private static int idPedido = 1;
    private int id;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private StatusPedido statusPedido;
    private double total;

    private StatusPagamento statusPagamento;
    private INotificadorPedido notificadorPedido;

    public Pedido(Cliente cliente, INotificadorPedido notificadorPedido) {
        this.id = idPedido++;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.statusPedido = StatusPedido.ABERTO;
        this.total = 0.0;
        this.notificadorPedido = notificadorPedido;
    };

    public Pedido(Cliente clientePedido) {
    }

    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public StatusPedido getStatusPedido() { return statusPedido; }
    public double getTotal() { return total; }
    public List<ItemPedido> getItens() { return itens; }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
        notificadorPedido.notificadorPedido("O status do pedido " + id + " foi alterado para " + statusPedido, cliente.getEmail());
    }

    public void adicionarItem(Produto produto, int quantidade, double valor) {
        ItemPedido itemPedido = new ItemPedido(produto, quantidade, valor);
        itens.add(itemPedido);
        total += itemPedido.calcularTotal();
        notificadorPedido.notificadorPedido("Item adicionado: " + produto.getNome(), cliente.getEmail());
    }

    public void removerItem(ItemPedido itemPedido) {
        itens.remove(itemPedido);
        total -= itemPedido.calcularTotal();
        notificadorPedido.notificadorPedido("Item removido: " + itemPedido.getProduto().getNome(), cliente.getEmail());
    }

    public void alterarQuantidadeItem(ItemPedido itemPedido, int novaQuantidade) {
        itemPedido.setQuantidade(novaQuantidade);
        total = 0;
        for (ItemPedido ip : itens) {
            total += ip.calcularTotal();
        }
        notificadorPedido.notificadorPedido("Quantidade alterada para " + novaQuantidade + " do item " + itemPedido.getProduto().getNome(), cliente.getEmail());
    }

    public void exibirDetalhes() {
        System.out.println("***Detalhes do Pedido***");
        System.out.println("ID do Pedido: " + this.id);
        System.out.println("Cliente: " + this.cliente.getNome());
        System.out.println("Status do Pedido: " + this.statusPedido);
        System.out.println("Total: " + this.total);
        System.out.println("\nLista de Itens do Pedido:");
        if (itens.isEmpty()) {
            System.out.println("Não possui nenhum item no pedido.");
        } else {
            for (ItemPedido item : itens) {
                System.out.println("Produto: " + item.getProduto().getNome() +
                        " | Quantidade: " + item.getQuantidade() +
                        " | Preço unitário: R$ " + item.getValor());
            }
        }
        System.out.println("*********************");
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente=" + cliente.getNome() +
                ", statusPedido=" + statusPedido +
                ", total=" + total +
                ", itens=" + itens +
                '}';
    }
}
