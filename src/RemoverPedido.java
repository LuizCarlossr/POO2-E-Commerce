public class RemoverPedido extends Pedido{
    public RemoverPedido(Cliente cliente) {
        super(cliente);
    }

    public void remover(Produto produto) {
        if (status == StatusPedido.ABERTO) {
            for (ItemPedido item : itens) {
                if (item.getProduto().equals(produto)) {
                    valorTotal -= item.calcularTotal();
                    itens.remove(item);
                    break;
                }
            }
        }
    }
}
