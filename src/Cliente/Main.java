package Cliente;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ClienteRepositorio clienteRepositorio = new ClienteRepositorioImpl();
        ClienteServico clienteServico = new ClienteServico(clienteRepositorio);

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
            System.out.println("5. Sair");
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
