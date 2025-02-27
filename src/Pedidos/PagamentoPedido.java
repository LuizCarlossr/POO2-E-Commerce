package Pedidos;
import Clientes.Cliente;

public class PagamentoPedido extends Pedido {
    public PagamentoPedido(Cliente cliente) {
        super(cliente);
    }

    public void realizarPagamento() {
        if (statusPagamento == StatusPagamento.AGUARDANDO_PAGAMENTO) {
            statusPagamento = StatusPagamento.PAGO;
            status = StatusPedido.FINALIZADO;
            System.out.println("Pedido.Pedido finalizado, em breve será entregue");
        } else {
            System.out.println("Pagamento não efetuado, estamos aguardando o Pagamento");
        }
    }
}
