package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import model.Cliente;

public class ClienteRepository implements RepositoryBase<Cliente> {
    private static final List<Cliente> clientes = new ArrayList<>();

    private final String UUID_PREFIX = "cli-";

    @Override
    public String criarUUID() {
        return UUID_PREFIX + UUID.randomUUID().toString();
    }

    @Override
    public void salvar(Cliente entity) {
        entity.setId(this.criarUUID());
        clientes.add(entity);
    }

    @Override
    public void deletar(String id) {
        clientes.removeIf((cliente) -> cliente.getId().equals(id));
    }

    @Override
    public List<Cliente> encontrarTodos() {
        return clientes;
    }

    @Override
    public Optional<Cliente> pegarPorId(String id) {
        return clientes.stream().filter(cliente -> cliente.getId().equals(id)).findFirst();
    }

    @Override
    public void atualizar(String id, Cliente entity) throws IllegalArgumentException {
        Optional<Cliente> optionalCliente = this.pegarPorId(id);
        
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            cliente.setNome(entity.getNome());
            cliente.setEmail(entity.getEmail());
            cliente.setTelefone(entity.getTelefone());
            cliente.setCpf(entity.getCpf());
            cliente.setDataNascimento(entity.getDataNascimento());
        } else {
            throw new IllegalArgumentException("Cliente com ID " + id + " não encontrado.");
        }
    }
}
