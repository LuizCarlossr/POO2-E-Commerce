package Cliente;

public class ClientePessoaFisica extends Cliente {

    private String cpf;

    public ClientePessoaFisica(String nome, String endereco, String telefone, String email, String cpf) {
        super(nome, endereco, telefone, email, "Pessoa Física", cpf);

        if (!isCpfValido(cpf)) {
            throw new IllegalArgumentException("CPF inválido: " + cpf);
        }
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (!isCpfValido(cpf)) {
            throw new IllegalArgumentException("CPF inválido: " + cpf);
        }
        this.cpf = cpf;
    }

    private boolean isCpfValido(String cpf) {

        if (cpf == null || cpf.length() != 11 || !cpf.matches("[0-9]+")) {
            return false;
        }

        int soma = 0;
        int digito;
        int resto;

        try {
            for (int i = 0; i < 9; i++) {
                soma += Integer.parseInt(String.valueOf(cpf.charAt(i))) * (10 - i);
            }

            resto = 11 - (soma % 11);
            digito = (resto == 10 || resto == 11) ? 0 : resto;

            if (digito != Integer.parseInt(String.valueOf(cpf.charAt(9)))) {
                return false;
            }

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Integer.parseInt(String.valueOf(cpf.charAt(i))) * (11 - i);
            }

            resto = 11 - (soma % 11);
            digito = (resto == 10 || resto == 11) ? 0 : resto;

            if (digito != Integer.parseInt(String.valueOf(cpf.charAt(10)))) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
        return true;
    }
}

