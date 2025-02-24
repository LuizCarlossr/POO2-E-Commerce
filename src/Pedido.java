import java.util.ArrayList;
import java.util.List;

public class Pedido {
    Cliente cliente;
    protected List<ItemPedido> itens;
    protected StatusPedido status;
    protected StatusPagamento statusPagamento;
    protected double valorTotal;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.status = StatusPedido.ABERTO;
        this.statusPagamento = StatusPagamento.AGUARDANDO_PAGAMENTO;
        this.valorTotal = 0;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}
