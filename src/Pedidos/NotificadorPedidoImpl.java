package Pedidos;

public class NotificadorPedidoImpl implements NotificadorPedido {
    @Override
    public void notificadorPedido(String mensagem, String email) {
        System.out.println("Mensagem: " + mensagem);
        System.out.println("Email: " + email);
    }
}
