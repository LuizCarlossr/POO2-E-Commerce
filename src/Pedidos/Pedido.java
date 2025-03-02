package Pedidos;

import Clientes.Cliente;
import Clientes.Notificador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido implements Notificador {
    private int id;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private Date dataCriacao;
    private StatusPedido statusPedido;
    private StatusPagamento statusPagamento;
    private Notificador notificador;

    public Pedido(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.dataCriacao = new Date();
        this.statusPedido = StatusPedido.ABERTO;
        this.statusPagamento = StatusPagamento.PENDENTE;
        this.notificador = new Notificador() {
            @Override
            public void notificar(Cliente cliente, String mensagem) {

            }
        };
    }

    public void adicionarPedido(ItemPedido item) {
        this.itens.add(item);
    }

    public void removerPedido(ItemPedido item) {
        this.itens.remove(item);
    }

    public void alterarQuanttidade(ItemPedido item, int novaQuantidade) {
        item.setQuantidade(novaQuantidade);
    }

    public void finalizarPedido() {
        if (itens.isEmpty() || calcularTotal() <= 0) {
            throw new IllegalArgumentException("O carrinho de compra deve ter um pedido com algum valor. ");
        }
        this.statusPedido = StatusPedido.AGUARDANDO_PAGAMENTO;
    }

    public void efetuarPagamento() {
        if (statusPedido == StatusPedido.AGUARDANDO_PAGAMENTO) {
            this.statusPagamento = StatusPagamento.PAGO;
            this.statusPedido = StatusPedido.FINALIZADO;
        }
    }

    public void entregarPedido() {
        if (statusPagamento == StatusPagamento.PAGO) {
            this.statusPedido = StatusPedido.FINALIZADO;
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemPedido item : itens) {
            total += item.calcularTotal();
        }
        return total;
    }

    @Override
    public void notificar(Cliente cliente, String mensagem) {

    }
}
