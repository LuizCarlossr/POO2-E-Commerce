package Cliente;

public class NotificadorSMS implements Notificador {

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.println("Enviando SMS para " + cliente.getTelefone());
        System.out.println("Mensagem: " + mensagem);
    }
}
