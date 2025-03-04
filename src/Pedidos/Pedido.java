package Pedidos;

import Clientes.Cliente;
import Clientes.Notificador;
import Produtos.Produto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
    private static int idPedido = 1;
    private int id;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private StatusPedido statusPedido;
    private double total;

    //private StatusPagamento statusPagamento;
    //private Notificador notificador;

    public Pedido(Cliente cliente) {
        this.id = idPedido++;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.statusPedido = StatusPedido.ABERTO;
        this.total = 0.0;
    };

    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public StatusPedido getStatusPedido() { return statusPedido; }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido; }

    public List<ItemPedido> getItens() { return itens; }

    public double getTotal() { return total; }

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
        itemPedido = new ItemPedido(itemPedido.getProduto(), novaQuantidade, itemPedido.getValor());
        total = 0;
        for (ItemPedido ip : itens) {
            total += ip.getValor();
        }
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id + ", cliente=" + cliente.getNome() + "statusPedido=" + statusPedido +
                ", total=" + total + ", itens=" + itens + '}';
    }
}
