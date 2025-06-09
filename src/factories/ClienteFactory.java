package factories;

import java.util.Date;
import model.Cliente;

public class ClienteFactory {
    public static Cliente criarCliente(String id, String nome, String email, String telefone, String cpf, Date dataNascimento) {
        return new Cliente(id, nome, email, telefone, cpf, dataNascimento);
    }
}
