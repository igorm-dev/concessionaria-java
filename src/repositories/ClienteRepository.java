package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import model.Cliente;
import utils.Desserializador;
import utils.Serilizador;

public class ClienteRepository {
    private final String UUID_PREFIX = "cli-";
    private final String caminhoArquivo = "clientes.ser";

    public String criarUUID() {
        return UUID_PREFIX + UUID.randomUUID().toString();
    }

    public void salvar(Cliente entity) {
        try {
            entity.setId(this.criarUUID());
    
            List<Cliente> clientes = this.encontrarTodos();
            clientes.add(entity);
    
            Serilizador.salvarCliente(clientes, this.caminhoArquivo);
        } catch (Exception e) {
            System.err.println("Erro ao salvar cliente: " + e.getMessage());
            throw new RuntimeException("Erro ao salvar cliente", e);
        }
    }

    public void deletar(String id) {
        try {
            List<Cliente> clientes = Desserializador.carregarCliente(this.caminhoArquivo);
            clientes.removeIf((carro) -> carro.getId().equals(id));
            Serilizador.salvarCliente(clientes, this.caminhoArquivo);
        } catch (Exception e) {
            System.err.println("Erro ao deletar cliente: " + e.getMessage());
            throw new RuntimeException("Erro ao deletar cliente", e);
        }
    }

    public List<Cliente> encontrarTodos() throws Exception {
        try {
            List<Cliente> clientes = Desserializador.carregarCliente(this.caminhoArquivo);

            if (clientes == null) {
                return new ArrayList<>();
            }

            return clientes;
        } catch (Exception e) {
            System.err.println("Erro ao carregar clientes: " + e.getMessage());
            throw new Exception("Erro ao carregar clientes");
        }
    }

    public Optional<Cliente> pegarPorId(String id) {
        try {
            List<Cliente> clientes = Desserializador.carregarCliente(this.caminhoArquivo);
            return clientes.stream().filter(carro -> carro.getId().equals(id)).findFirst();
        } catch (Exception e) {
            System.err.println("Erro ao buscar cliente: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar cliente");
        }
    }

    public void atualizar(String id, Cliente entity) throws IllegalArgumentException {
        try {
            List<Cliente> clientes = Desserializador.carregarCliente(this.caminhoArquivo);
            
            Optional<Cliente> optionalCliente = clientes.stream().filter(cliente -> cliente.getId().equals(id)).findFirst();

            if (optionalCliente.isPresent()) {
                Cliente cliente = optionalCliente.get();
                cliente.setNome(entity.getNome());
                cliente.setEmail(entity.getEmail());
                cliente.setTelefone(entity.getTelefone());
                cliente.setCpf(entity.getCpf());
                cliente.setDataNascimento(entity.getDataNascimento());

                Serilizador.salvarCliente(clientes, this.caminhoArquivo);
            } else {
                throw new IllegalArgumentException("cliente com ID " + id + " não encontrada.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao atualizar cliente: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar cliente");
        }
    }
}
