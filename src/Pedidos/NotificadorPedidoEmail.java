package Pedidos;

public class NotificadorPedidoEmail implements INotificadorPedido {
    @Override
    public void notificadorPedido(String mensagem, String email) {
        System.out.println("Enviando email para " + email + ": " + mensagem);
    }
}
