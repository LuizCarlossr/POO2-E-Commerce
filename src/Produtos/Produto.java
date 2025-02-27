package Produtos;

public class Produto implements IProduto {
    private int id;
    private String nome;
    private double preco;
    private double precoVenda;

    public Produto(String nome, double preco) {
        this.id = 0;
        this.nome = nome;
        setPreco(preco);
        this.precoVenda = preco;
    }


    public Produto(int id, String nome, double preco, double precoVenda) {
        this.id = id;
        this.nome = nome;
        setPreco(preco);
        setPrecoVenda(precoVenda);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco <= 0) {
            throw new IllegalArgumentException("O preço do produto deve ser maior que zero.");
        }
        this.preco = preco;
    }

      public void setPrecoVenda(double precoVenda) {
        if (precoVenda > 0) {
            this.precoVenda = precoVenda;
        } else {
            throw new IllegalArgumentException("O preço de venda deve ser maior que zero.");
        }
    }

     public double getPrecoVenda() {
        return precoVenda;
    }

    @Override
    public String toString() {
        return String.format("Produto{id=%d, nome='%s', preco=%.2f, precoVenda=%.2f}",
                id, nome, preco, precoVenda);
    }
}
