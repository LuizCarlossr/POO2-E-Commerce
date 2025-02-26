package Produtos;

import java.util.List;

public interface ProdutoRepositorio {
    public void adicionar(Produto produto);
    List<Produto> listarTodos();
    Produto buscarPorId(int id);
    public void atualizar(Produto produto);
    public void remover(int id);
}
