package factories;

import model.Cliente;

public abstract class ClienteFactory {
    public static Cliente criarClientePersistido(String id, String nome, String email, String telefone, String cpf, String dataNascimento) {
        return new Cliente(id, nome, email, telefone, cpf, dataNascimento);
    }

    public static Cliente criarNovoCliente(String nome, String email, String telefone, String cpf, String dataNascimento) {
        return new Cliente(nome, email, telefone, cpf, dataNascimento);
    }
}