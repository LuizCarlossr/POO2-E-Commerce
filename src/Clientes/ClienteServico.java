package Clientes;

import java.util.List;

public class ClienteServico {
    private ClienteRepositorio clienteRepositorio;

    public ClienteServico(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    public void cadastrarCliente(String nome, String endereco, String telefone, String email, String tipo, String documento) {
        Cliente cliente = null;
        if (tipo.equalsIgnoreCase("PF")) {
            cliente = new ClientePessoaFisica(nome, endereco, telefone, email, documento);
        }

        clienteRepositorio.adicionar(cliente);
        System.out.println("Cliente cadastrado: " + nome);
    }

    public List<Cliente> listarClientes() {
        return clienteRepositorio.listarTodos();
    }

    public void atualizarCliente(String nome, String novoNome, String novoEndereco, String novoTelefone, String novoEmail, String novoCpf) {
        Cliente cliente = clienteRepositorio.buscarPorNome(nome);
        if (cliente != null) {
            cliente.setNome(novoNome);
            cliente.setEndereco(novoEndereco);
            cliente.setTelefone(novoTelefone);
            cliente.setEmail(novoEmail);
            if (cliente instanceof ClientePessoaFisica) {
                ((ClientePessoaFisica) cliente).setCpf(novoCpf);
            }
            clienteRepositorio.atualizar(cliente);
            System.out.println("Cliente atualizado com sucesso!");
        } else {
            System.out.println("Cliente não encontrado!");
        }
    }
    public void removerCliente(String nome) {
        clienteRepositorio.remover(nome);
    }
}

