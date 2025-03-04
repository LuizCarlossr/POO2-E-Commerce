package Pedidos;

import Clientes.Cliente;
import Produtos.Produto;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int idPedido = 1;
    private int id;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private boolean finalizado;
    private boolean pago;
    private boolean entregue;
    private StatusPedido statusPedido;
    private double total;

    public Pedido(Cliente cliente) {
        this.id = idPedido++;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.finalizado = false;
        this.pago = false;
        this.entregue = false;
        this.statusPedido = StatusPedido.ABERTO;
        this.total = 0.0;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public double getTotal() {
        return total;
    }

    public void adicionarItem(Produto produto, int quantidade, double valor) {
        ItemPedido itemPedido = new ItemPedido(produto, quantidade, valor);
        itens.add(itemPedido);
        total += itemPedido.calcularTotal();
    }

    public void removerItem(ItemPedido itemPedido) {
        itens.remove(itemPedido);
        total -= itemPedido.calcularTotal();
    }

    public void alterarQuantidadeItem(ItemPedido itemPedido, int novaQuantidade) {
        itemPedido.setQuantidade(novaQuantidade);
        total = 0;
        for (ItemPedido ip : itens) {
            total += ip.calcularTotal();
        }
    }

    public void exibirDetalhes() {
        System.out.println("***Detalhes do Pedido***");
        System.out.println("ID do Pedido: " + this.id);
        System.out.println("Cliente: " + (finalizado ? "Finalizado" : "Em andamento"));
        System.out.println("Pago: " + (pago ? "Sim" : "Não"));
        System.out.println("Entregue: " + (entregue ? "Sim" : "Não"));

        System.out.println("\nLista de Itens do Pedido");
        if (itens.isEmpty()) {
            System.out.println("Não possui nenhum item no pedido.");
        } else {
            for (ItemPedido itemPedido : itens) {
                System.out.println("Produto: " + itemPedido.getProduto().getNome() +
                        " | Quantidade: " + itemPedido.getQuantidade() + " | Preço unitário: R$ " + itemPedido.getValor());
            }
        }
        System.out.println("*********************");
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id + ", cliente=" + cliente.getNome() +
                ", statusPedido=" + statusPedido +
                ", total=" + total + ", itens=" + itens + '}';
    }
}
