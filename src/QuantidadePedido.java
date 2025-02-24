public class QuantidadePedido extends Pedido{
    public QuantidadePedido(Cliente cliente) {
        super(cliente);
    }

    public void alterarQuantidade(Produto produto, int novaQuantidade) {
        if (status == StatusPedido.ABERTO) {
            for (ItemPedido item : itens) {
                if (item.getProduto().equals(produto)) {
                    valorTotal -= item.calcularTotal();
                    item.setQuantidade(novaQuantidade);
                    valorTotal += item.calcularTotal();
                    break;
                }
            }
        }
    }
}
