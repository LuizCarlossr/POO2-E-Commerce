package Clientes;

import java.util.List;

public interface ClienteRepositorio {
    void adicionar(Cliente cliente);
    List<Cliente> listarTodos();
    Cliente buscarPorNome(String nome);
    void atualizar(Cliente cliente);
    void remover(String nome);
}

