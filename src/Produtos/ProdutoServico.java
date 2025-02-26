package Produtos;

import java.util.ArrayList;
import java.util.List;

public class ProdutoServico {
    private ProdutoRepositorio produtoRepositorio;

    public ProdutoServico(ProdutoRepositorio produtoRepositorio) {
        this.produtoRepositorio = produtoRepositorio;
    }

    public void adicionarProduto(String nome,double preco, int id) {
        Produto produto = produtoRepositorio.buscarPorId(id);
        if (produto != null) {
            produto.setNome(nome);
            produto.setPreco(preco);
            produtoRepositorio.atualizar(produto);
            System.out.println("Produto atualizado: " + nome);
        } else {
            System.out.println("Produto não existe. ");
        }
    }

    public void atualizarProduto(int id, String nome, double preco) {
        Produto produto = produtoRepositorio.buscarPorId(id);
        if (produto != null) {
            produto.setNome(nome);
            produto.setPreco(preco);
            produtoRepositorio.atualizar(produto);
            System.out.println("Produto atualizado: " + nome);
        } else {
            System.out.println("Produto não existe");
        }
    }

    public void remover(int id) {
        Produto produto = produtoRepositorio.buscarPorId(id);
        if (produto != null) {
            produtoRepositorio.remover(id);
            System.out.println("Produto removido. ");
        } else {
            System.out.println("Produto não existe. ");
        }
    }

    public Produto buscarProdutoId(int id) {
        Produto produto = produtoRepositorio.buscarPorId(id);
        if (produto != null) {
            return produto;
        } else {
            System.out.println("Produto não existe. ");
            return null;
        }
    }
}
