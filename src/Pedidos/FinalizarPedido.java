package Pedidos;
import Clientes.Cliente;

public class FinalizarPedido extends Pedido {
    public FinalizarPedido(Cliente cliente) {
        super(cliente);
    }

    public void finalizarPedido() {
        if (itens.isEmpty() || valorTotal <= 0) {
            System.out.println("Pedido.Pedido não finalizado. Adicione o pedido.");
            return;
        }
        status = StatusPedido.AGUARDANDO_PAGAMENTO;
        System.out.println("Aguardando Pagamento");
    }
}
