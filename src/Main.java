import Clientes.*;
import Produtos.*;
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
            System.out.println("5. Cadastrar produtos com desconto");
            System.out.println("6. Listar produtos com desconto");
            System.out.println("7. Atualizar produto");
            System.out.println("8. Remover produto");
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
                    System.out.print("Nome do cliente para atualizar: ");
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
                    System.out.print("Digite o nome do produto com desconto: ");
                    String nomeProdutoComDesconto = scanner.nextLine();
                    System.out.print("Digite o valor original do produto: ");
                    double precoProdutoComDesconto = scanner.nextDouble();
                    System.out.print("Digite o percentual de desconto (0-100): ");
                    double percentualDesconto = scanner.nextDouble();
                    scanner.nextLine();
                    ProdutoComDesconto produtoComDesconto = new ProdutoComDesconto(nomeProdutoComDesconto, precoProdutoComDesconto, percentualDesconto);
                    produtoRepositorio.adicionar(produtoComDesconto);
                    System.out.println("Produto com desconto cadastrado no sistema.");
                    break;

                case 6:
                    List<Produto> produtosComDesconto = produtoRepositorio.listarTodos();
                    if (produtosComDesconto.isEmpty()) {
                        System.out.println("Nenhum produto com desconto cadastrado.");
                    } else {
                        System.out.println("Produtos com desconto cadastrados:");
                        for (Produto p : produtosComDesconto) {
                            if (p instanceof ProdutoComDesconto) {
                                System.out.println(p);
                            }
                        }
                    }
                    break;

                case 7:
                    System.out.print("ID do produto a ser atualizado: ");
                    int produtoId = scanner.nextInt();
                    scanner.nextLine();
                    Produto produtoExistente = produtoRepositorio.buscarPorId(produtoId);

                    if (produtoExistente == null) {
                        System.out.println("Produto não encontrado!");
                    } else {
                        if (produtoExistente instanceof ProdutoComDesconto) {
                            ProdutoComDesconto produtoComDescontoAtualizado = (ProdutoComDesconto) produtoExistente;
                            System.out.println("O que você deseja atualizar?");
                            System.out.println("1. Nome do novo produto");
                            System.out.println("2. Preço do novo produto");
                            System.out.println("3. Percentual de desconto");
                            System.out.print("Escolha uma opção: ");
                            int opcaoAtualizacao = scanner.nextInt();
                            scanner.nextLine();

                            switch (opcaoAtualizacao) {
                                case 1:
                                    System.out.print("Digite o novo nome do produto: ");
                                    String novoNomeProduto = scanner.nextLine();
                                    produtoComDescontoAtualizado.setNome(novoNomeProduto);
                                    produtoRepositorio.atualizar(produtoComDescontoAtualizado);
                                    System.out.println("Nome do produto com desconto atualizado com sucesso!");
                                    break;

                                case 2:
                                    System.out.print("Digite o novo preço do produto: ");
                                    double novoPrecoProduto = scanner.nextDouble();
                                    produtoComDescontoAtualizado.setPreco(novoPrecoProduto);
                                    produtoComDescontoAtualizado.setPrecoVenda(novoPrecoProduto * (1 - produtoComDescontoAtualizado.getPercentualDesconto() / 100));
                                    produtoRepositorio.atualizar(produtoComDescontoAtualizado);
                                    System.out.println("Preço do produto com desconto atualizado com sucesso!");
                                    break;

                                case 3:
                                    System.out.print("Digite o novo percentual de desconto: ");
                                    double novoDesconto = scanner.nextDouble();
                                    produtoComDescontoAtualizado.setPercentualDesconto(novoDesconto);
                                    produtoComDescontoAtualizado.setPrecoVenda(produtoComDescontoAtualizado.getPreco() * (1 - novoDesconto / 100));
                                    produtoRepositorio.atualizar(produtoComDescontoAtualizado);
                                    System.out.println("Desconto do produto atualizado com sucesso!");
                                    break;

                                default:
                                    System.out.println("Opção inválida!");
                            }
                        } else {
                            System.out.println("O produto não é um produto com desconto!");
                        }
                    }
                    break;

                case 8:
                    System.out.print("ID do produto a ser removido: ");
                    int idRemoverProduto = scanner.nextInt();
                    scanner.nextLine();
                    Produto produtoRemover = produtoRepositorio.buscarPorId(idRemoverProduto);

                    if (produtoRemover == null) {
                        System.out.println("Produto não encontrado!");
                    } else {
                        System.out.print("Você tem certeza que deseja remover o produto? (S/N): ");
                        String confirmacao = scanner.nextLine();
                        if (confirmacao.equalsIgnoreCase("S")) {
                            produtoRepositorio.remover(produtoRemover.getId());
                            System.out.println("Produto removido com sucesso!");
                        } else {
                            System.out.println("Remoção cancelada!");
                        }
                    }
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
