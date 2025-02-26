package Clientes;

public class NotificadorWhatsApp implements Notificador {

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.println("Enviando mensagem via WhatsApp para " + cliente.getTelefone());
        System.out.println("Mensagem: " + mensagem);
    }
}
