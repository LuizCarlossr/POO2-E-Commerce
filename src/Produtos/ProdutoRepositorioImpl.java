package Produtos;

import java.util.ArrayList;
import java.util.List;

public class ProdutoRepositorioImpl implements ProdutoRepositorio{
    private List<Produto> produtos = new ArrayList<>();

    @Override
    public void adicionar(Produto produto) {
        produtos.add(produto);
        System.out.println("Produto adicionado: " + produto.getNome());
    }

    @Override
    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos);
    }

    @Override
    public Produto buscarPorId(int id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    @Override
    public void atualizar(Produto produto) {
        Produto existente = buscarPorId(produto.getId());
        if (existente != null) {
            existente.setNome(produto.getNome());
            existente.setPreco(produto.getPreco());
        }
    }

    @Override
    public void remover(int id) {
        Produto produto = buscarPorId(id);
        if (produto != null) {
            produtos.remove(produto);
            System.out.println("Produto removido: " + produto.getNome());
        }
    }
}
