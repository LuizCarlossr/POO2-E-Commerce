package Pedido;
import Cliente.Cliente;

public class AdicionarPedido extends Pedido {
    public AdicionarPedido(Cliente cliente) {
        super(cliente);
    }

    public void adicionar(Produto produto, int quantidade, double precoVenda) {
        if (status == StatusPedido.ABERTO) {
            ItemPedido item = new ItemPedido(produto, quantidade, precoVenda);
            itens.add(item);
            valorTotal += item.calcularTotal();
        }
    }
}
