import Clientes.*;
import Produtos.Produto;
import Produtos.ProdutoRepositorio;
import Produtos.ProdutoRepositorioImpl;
import Produtos.ProdutoServico;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ClienteRepositorio clienteRepositorio = new ClienteRepositorioImpl();
        ClienteServico clienteServico = new ClienteServico(clienteRepositorio);
        ProdutoRepositorio produtoRepositorio = new ProdutoRepositorioImpl();
        ProdutoServico produtoServico = new ProdutoServico(produtoRepositorio);

        Notificador notificadorEmail = new NotificadorEmail();
        Notificador notificadorWhatsApp = new NotificadorWhatsApp();
        Notificador notificadorSMS = new NotificadorSMS();

        Scanner scanner = new Scanner(System.in);



        boolean continuar = true;

        while (continuar) {
            System.out.println("--- Menu ---");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Atualizar cliente");
            System.out.println("4. Remover cliente");
            System.out.println("5. Cadastrar produtos");
            System.out.println("6. Listar produtos");
            System.out.println("7. Atualizar produto");
            System.out.println("8. Remover produto");;
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:

                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String endereco = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Insira 'PF' para pessoa física: ");
                    String tipo = scanner.nextLine();
                    System.out.print("Insira o número do CPF : ");
                    String documento = scanner.nextLine();
                    clienteServico.cadastrarCliente(nome, endereco, telefone, email, tipo, documento);

                    Cliente cliente = new Cliente(nome, endereco, telefone, email, tipo, documento);
                    notificadorEmail.notificar(cliente, "Email enviado com sucesso!");
                    notificadorWhatsApp.notificar(cliente, "WhatsApp enviado com sucesso!");
                    notificadorSMS.notificar(cliente, "SMS enviado com sucesso!");
                    break;

                case 2:

                    List<Cliente> clientes = clienteServico.listarClientes();
                    if (clientes.isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado.");
                    } else {
                        System.out.println("Clientes cadastrados:");
                        for (Cliente c : clientes) {
                            c.mostrarDetalhes();
                        }
                    }
                    break;

                case 3:

                    System.out.print("Nome do cliente atual para atualizar: ");
                    String nomeAtualizar = scanner.nextLine();
                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Novo endereço: ");
                    String novoEndereco = scanner.nextLine();
                    System.out.print("Novo telefone: ");
                    String novoTelefone = scanner.nextLine();
                    System.out.print("Novo email: ");
                    String novoEmail = scanner.nextLine();
                    System.out.print("Novo CPF: ");
                    String novoCpf = scanner.nextLine();
                    clienteServico.atualizarCliente(nomeAtualizar, novoNome, novoEndereco, novoTelefone, novoEmail, novoCpf);

                    Cliente clienteAtualizado = new Cliente(novoNome, novoEndereco, novoTelefone, novoEmail, "PF", novoCpf);
                    notificadorEmail.notificar(clienteAtualizado, "Seu Email foi atualizado!");
                    notificadorWhatsApp.notificar(clienteAtualizado, "Seu WhatsApp foi atualizado!");
                    notificadorSMS.notificar(clienteAtualizado, "Seu SMS foi atualizado!");
                    break;

                case 4:

                    System.out.print("Nome do cliente a remover: ");
                    String nomeRemover = scanner.nextLine();
                    clienteServico.removerCliente(nomeRemover);

                    Cliente clienteRemovido = new Cliente(nomeRemover, "Removido", "0", "removido@exemplo.com", "PF", "00000000000");
                    notificadorEmail.notificar(clienteRemovido, "Seu cadastro foi removido.");
                    notificadorWhatsApp.notificar(clienteRemovido, "Seu cadastro foi removido.");
                    notificadorSMS.notificar(clienteRemovido, "Seu cadastro foi removido.");
                    break;

                case 5:
                    System.out.println("Digite o nome do produto: ");
                    String nomeProduto = scanner.nextLine();
                    System.out.println("Digite o valor do produto: ");
                    double precoProduto = scanner.nextDouble();
                    scanner.nextLine();
                    Produto produto = new Produto(nomeProduto,precoProduto);
                    produtoRepositorio.adicionar(produto);
                    System.out.println("Produto cadastrado no sistema.");
                    break;

                case 6:
                    List<Produto> produtos = produtoRepositorio.listarTodos();
                    if (produtos.isEmpty()) {
                        System.out.println("Nenhum produto cadastrado.");
                    }  else {
                        System.out.println("Produto cadastrado: ");
                        for (Produto p  : produtos) {
                            System.out.println(p);
                        }
                    }
                    break;

                case 7:
                    System.out.println("ID do produto a ser atualizado: ");
                    int produtoId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Digite o nome do produto: ");
                    String novoNomeProduto = scanner.nextLine();
                    System.out.println("Atualize o preço do produto: ");
                    double novoPreco = scanner.nextDouble();
                    scanner.nextLine();
                    produtoServico.atualizarProduto(produtoId,novoNomeProduto, novoPreco);

                case 8:
                    System.out.println("Id do produto que será removido: ");
                    int idRemoverProduto = scanner.nextInt();
                    scanner.nextLine();
                    produtoServico.remover(idRemoverProduto);
                    break;

                case 9:
                    continuar = false;
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}
