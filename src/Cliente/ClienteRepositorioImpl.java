package Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepositorioImpl implements ClienteRepositorio {
    private List<Cliente> clientes = new ArrayList<>();

    @Override
    public void adicionar(Cliente cliente) {
        if (cliente != null) {
            clientes.add(cliente);
            System.out.println("Cliente adicionado: " + cliente.getNome());
        }
    }

    @Override
    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientes);
    }

    @Override
    public Cliente buscarPorNome(String nome) {
        for (Cliente cliente : clientes) {
            if (cliente.getNome().equalsIgnoreCase(nome)) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public void atualizar(Cliente cliente) {
        Cliente existente = buscarPorNome(cliente.getNome());
        if (existente != null) {

            existente.setNome(cliente.getNome());
            existente.setEndereco(cliente.getEndereco());
            existente.setTelefone(cliente.getTelefone());
            existente.setEmail(cliente.getEmail());
            existente.setDocumento(cliente.getDocumento());
        }
    }

    @Override
    public void remover(String nome) {
        Cliente cliente = buscarPorNome(nome);
        if (cliente != null) {
            clientes.remove(cliente);
            System.out.println("Cliente removido: " + nome);
        }
    }
}

