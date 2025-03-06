package Pedidos;

import Produtos.Produto;

public class ItemPedido {
    private Produto produto;
    private int quantidade;
    private double valor;

    public ItemPedido(Produto produto, int quantidade, double valor) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int novaQuantidade) {
        this.quantidade = novaQuantidade; }

    public double getValor() {
        return valor;
    }

    public double calcularTotal() {
        return valor * quantidade;
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "produto=" + produto.getNome() +
                ", quantidade=" + quantidade +
                ", valor=" + valor +
                ", total=" + calcularTotal() + '}';
    }
}
