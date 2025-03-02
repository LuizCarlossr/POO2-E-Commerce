package Pedidos;

import Produtos.Produto;

public class ItemPedido {
    private Produto produto;
    private int quantidade;
    private double precoUnidade;

    public ItemPedido(Produto produto, int quantidade, double precoUnidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnidade = precoUnidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnidade() {
        return precoUnidade;
    }

    public double calcularTotal() {
        return  precoUnidade * quantidade;
    }
}
