package Cliente;

public class Cliente {
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private String tipo;
    private String documento;

    public Cliente(String nome, String endereco, String telefone, String email, String tipo, String documento) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.tipo = tipo;
        this.documento = documento;
    }

     public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void mostrarDetalhes() {
        System.out.println("Nome: " + nome + ", Endereço: " + endereco + ", Telefone: " + telefone + ", Email: " + email + ", Tipo: " + tipo + ", Número do Documento: " + documento);
    }
}
