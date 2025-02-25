package Cliente;

public class NotificadorCliente {
    private Notificador notificador;

    public NotificadorCliente(Notificador notificador) {
        this.notificador = notificador;
    }

    public void notificarCliente(Cliente cliente, String mensagem) {
        notificador.notificar(cliente, mensagem);
    }
}

