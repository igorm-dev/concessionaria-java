package controllers;

import java.util.List;
import model.Cliente;
import repositories.ClienteRepository;

public class ClienteController {
    private final ClienteRepository repository;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }
    
    public void salvar(Cliente cliente) throws Exception {
        try {
            this.repository.salvar(cliente);
        } catch (Exception e) {
            System.err.println("Erro ao salvar cliente: " + e.getMessage());
            this.repository.salvar(cliente);
        }
    }

    public void deletar(String id) throws Exception {
        try {
            this.repository.deletar(id);
        } catch (Exception e) {
            System.err.println("Erro ao deletar cliente: " + e.getMessage());
            throw new Exception("Erro ao deletar cliente com ID: " + id);
        }
    }

    public List<Cliente> Listar() throws Exception {
        try {
            return this.repository.encontrarTodos();
        } catch (Exception e) {
            System.err.println("Erro ao listar clientes: " + e.getMessage());
            throw new Exception("Erro ao listar clientes");
        }
    }

    public Cliente buscarPorId(String id) throws Exception {
        try {
            return this.repository.pegarPorId(id)
                .orElseThrow(() -> new Exception("Cliente não encontrado com ID: " + id));
        } catch (Exception e) {
            System.err.println("Erro ao buscar cliente por id " + id + ", erro: " + e.getMessage());
            throw new Exception("Erro ao buscar cliente por id " + id);
        }
    }

    public void atualizar(String id, Cliente cliente) throws Exception {
        try {
            this.repository.atualizar(id, cliente);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao atualizar cliente: " + e.getMessage());
            throw new Exception("Erro ao atualizar cliente com ID: " + id);
        } catch (Exception e) {
            System.err.println("Erro inesperado ao atualizar cliente: " + e.getMessage());
            throw new Exception("Erro inesperado ao atualizar cliente com ID: " + id);
        }
    }
}
