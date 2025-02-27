package Produtos;

public class ProdutoComDesconto extends Produto {
    private double percentualDesconto;

    public ProdutoComDesconto(String nome, double preco, double percentualDesconto) {
        super(nome, preco);
        this.percentualDesconto = percentualDesconto;
        setPrecoVenda(getPreco() * (1 - percentualDesconto / 100));
    }

    public double getPercentualDesconto() {
        return percentualDesconto;
    }

    public void setPercentualDesconto(double percentualDesconto) {
        this.percentualDesconto = percentualDesconto;
        setPrecoVenda(getPreco() * (1 - percentualDesconto / 100));
    }

    @Override
    public String toString() {
        return String.format("ProdutoComDesconto{id=%d, nome='%s', preco=%.2f, precoVenda=%.2f, desconto=%.2f%%}",
                getId(), getNome(), getPreco(), getPrecoVenda(), percentualDesconto);
    }
}
