import Clientes.*;
import Pedidos.*;
import Produtos.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ClienteRepositorio clienteRepositorio = new ClienteRepositorioImpl();
        ClienteServico clienteServico = new ClienteServico(clienteRepositorio);
        ProdutoRepositorio produtoRepositorio = new ProdutoRepositorioImpl();
        ProdutoServico produtoServico = new ProdutoServico(produtoRepositorio);
        PedidoService pedidoService = new PedidoService(new INotificadorPedido() {
            @Override
            public void notificadorPedido(String mensagem, String email) { }
        });

        Notificador notificadorEmail = new NotificadorEmail();
        Notificador notificadorWhatsApp = new NotificadorWhatsApp();
        Notificador notificadorSMS = new NotificadorSMS();

        List<Cliente> clientes = clienteServico.listarClientes();
        List<Pedido> pedidos = new ArrayList<>();
        List<Produto> produtos = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("--- Menu ---");
            System.out.println(" 1. Cadastrar cliente");
            System.out.println(" 2. Listar clientes");
            System.out.println(" 3. Atualizar cliente");
            System.out.println(" 4. Remover cliente");
            System.out.println(" 5. Cadastrar produtos com desconto");
            System.out.println(" 6. Listar produtos com desconto");
            System.out.println(" 7. Atualizar produto");
            System.out.println(" 8. Remover produto");
            System.out.println(" 9. Criar Pedido");
            System.out.println("10. Adicionar Produto ao Pedido");
            System.out.println("11. Remover Produto do Pedido");
            System.out.println("12. Alterar Quantidade no Pedido");
            System.out.println("13. Lista de Pedidos cadastrados");
            System.out.println("14. Finalizar Pedido");
            System.out.println("15. Realizar Pagamento");
            System.out.println("16. Entregar Pedido");
            System.out.println("17. Sair");
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
                    clientes = clienteServico.listarClientes();
                    Cliente cliente = new Cliente(nome, endereco, telefone, email, tipo, documento);
                    notificadorEmail.notificar(cliente, "Email enviado com sucesso!");
                    notificadorWhatsApp.notificar(cliente, "WhatsApp enviado com sucesso!");
                    notificadorSMS.notificar(cliente, "SMS enviado com sucesso!");
                    break;

                case 2:
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
                    produtos.add(produtoComDesconto);
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
                    System.out.println("Digite o Nome do cliente para iniciar o pedido: ");
                    String nomePedido = scanner.nextLine();
                    Cliente clientePedido = null;
                    for (Cliente c : clientes) {
                        if (c.getNome().equalsIgnoreCase(nomePedido)) {
                            clientePedido = c;
                            break;
                        }
                    }
                    if(clientePedido != null) {
                         Pedido pedido = new Pedido(clientePedido);
                         pedidos.add(pedido);
                        System.out.println("Pedido criado. ID do Pedido: " + pedido.getId());
                    } else {
                        System.out.println("Cliente não encontrado");
                    }
                    break;


                case 10:
                    System.out.println("Digite o ID do pedido: ");
                    int idPedidoAdicionar = scanner.nextInt();
                    Pedido pedidoAdicionar = null;
                    for (Pedido pedido : pedidos) {
                        if (pedido.getId() == idPedidoAdicionar) {
                            pedidoAdicionar = pedido;
                            break;
                        }
                    }
                    if (pedidoAdicionar != null) {
                        System.out.println("Digite o ID do Produto para adicionar ao Pedido");
                        int idProdutoAdicionar = scanner.nextInt();
                        Produto produtoAdicionar = null;
                        for (Produto produto : produtos) {
                            if (produto.getId() == idProdutoAdicionar) {
                                produtoAdicionar = produto;
                                break;
                            }
                        }
                        if (pedidoAdicionar != null) {
                            System.out.println("Digite a quantidade: ");
                            int quantidadeAdicionar = scanner.nextInt();
                            System.out.println("Digite o valor da venda: ");
                            double valor = scanner.nextDouble();
                            PedidoService.adicionarItem(pedidoAdicionar, produtoAdicionar, quantidadeAdicionar, valor);
                            System.out.println(" Produto foi adicionado ao pedido.");
                        } else {
                            System.out.println("Produto não encontrado.");
                        } break;
                    }

                case 11:
                    System.out.println("Digite o ID do Pedido para remover: ");
                    int idPedidoRemover =  scanner.nextInt();
                    Pedido pedidoRemover = null;
                    for (Pedido pedido : pedidos) {
                        if(pedido.getId() == idPedidoRemover) {
                            pedidoRemover = pedido;
                            break;
                        }
                    }
                    if (pedidoRemover != null) {
                        System.out.println("Digite o ID do produto para remover: ");
                        int idProdutoRemover = scanner.nextInt();
                        ItemPedido itemRemover = null;
                        for (ItemPedido itemPedido : pedidoRemover.getItens()) {
                            if (itemPedido.getProduto().getId() == idProdutoRemover) {
                                itemRemover = itemPedido;
                                break;
                            }
                        }
                        if (itemRemover != null) {
                            pedidoService.removerItem(pedidoRemover, itemRemover);
                            System.out.println("Produto removido do pedido.");
                        } else {
                            System.out.println("Produto não encontrado no pedido.");
                        }
                    } else {
                        System.out.println("Pedido não encontrado");
                    }
                    break;

                case 12:
                    System.out.println("Digite a ID do Pedido para alterar: ");
                    int idPedidoAlterar = scanner.nextInt();
                    Pedido pedidoAlterar = null;
                    for (Pedido pedido : pedidos) {
                        if (pedido.getId() == idPedidoAlterar) {
                            pedidoAlterar = pedido;
                            break;
                        }
                    }
                    if(pedidoAlterar != null) {
                        System.out.println("Digite o ID do produto para alterar a quantidade: ");
                        int idProdutoAlterar = scanner.nextInt();
                        ItemPedido itemAlterar = null;
                        for (ItemPedido itemPedido : pedidoAlterar.getItens()) {
                            if (itemPedido.getProduto().getId() == idProdutoAlterar) {
                                itemAlterar = itemPedido;
                                break;
                            }
                        }
                        if (itemAlterar != null) {
                            System.out.println("Digite a nova quantidade: ");
                            int novaQuantidade = scanner.nextInt();
                            pedidoService.alterarQuantidadeItem(pedidoAlterar, itemAlterar, novaQuantidade);
                            System.out.println("Quantidade alterada.");
                        } else {
                            System.out.println("Produto não foi encontrado.");
                        }
                    } else {
                        System.out.println("Pedido não foi encontrado.");
                    }
                    break;

                case 13:
                    System.out.println("Lista de Pedidos cadastrados: ");
                    if (pedidos.isEmpty()) {
                        System.out.println("Não possui pedido cadastrado.");
                    } else {
                        for (Pedido pedido : pedidos) {
                            pedido.exibirDetalhes();
                        }
                    }

                case 14:
                    System.out.println("Digite o ID do pedido para finalizar: ");
                    int idPedidoFinalizar = scanner.nextInt();
                    Pedido pedidoFinalizar = null;
                    for (Pedido pedido : pedidos) {
                        if (pedido.getId() == idPedidoFinalizar) {
                            pedidoFinalizar = pedido;
                            break;
                        }
                    }
                    if (pedidoFinalizar != null) {
                        pedidoService.finalizarPedido(pedidoFinalizar);
                    } else {
                        System.out.println("Pedido não foi encontrado.");
                    }
                    break;

                case 15:
                    System.out.println("Digite o ID do Pedido para efetuar o pagamento: ");
                    int idPedidoPagar = scanner.nextInt();
                    Pedido pedidoPagar = null;
                    for (Pedido pedido : pedidos) {
                        if(pedido.getId() == idPedidoPagar) {
                            pedidoPagar = pedido;
                            break;
                        }
                    }
                    if (pedidoPagar != null) {
                        pedidoService.pagarPedido(pedidoPagar);
                    } else {
                        System.out.println("Pedido não foi encontrado.");
                    }
                    break;

                case 16:
                    System.out.println("Digite o ID do pedido para efetuar a entrega: ");
                    int idPedidoEntregar = scanner.nextInt();
                    scanner.nextLine();
                    Pedido pedidoEntregar = null;
                    for (Pedido pedido : pedidos) {
                        if ( pedido.getId() == idPedidoEntregar) {
                            pedidoEntregar = pedido;
                            break;
                        }
                    }
                    if (pedidoEntregar != null) {
                        pedidoService.entregarPedido(pedidoEntregar);
                    } else {
                        System.out.println("Pedido não foi encontrado.");
                    }
                    break;


                case 17:
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
