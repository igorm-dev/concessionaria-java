package controllers;

import java.util.List;
import model.Venda;
import repositories.VendaRepository;

public class VendaController {
    private final VendaRepository repository;

    public VendaController(VendaRepository repository) {
        this.repository = repository;
    }

    public void salvar(Venda venda) throws Exception {
        try {
            this.repository.salvar(venda);
        } catch (Exception e) {
            System.err.println("Erro ao salvar venda: " + e.getMessage());
            throw new Exception("Erro ao salvar venda");
        }
    }

    public void deletar(String id) throws Exception {
        try {
            this.repository.deletar(id);
        } catch (Exception e) {
            System.err.println("Erro ao deletar venda: " + e.getMessage());
            throw new Exception("Erro ao deletar venda com ID: " + id);
        }
    }

    public List<Venda> listar() throws Exception {
        try {
            return this.repository.encontrarTodos();
        } catch (Exception e) {
            System.err.println("Erro ao listar vendas: " + e.getMessage());
            throw new Exception("Erro ao listar vendas");
        }
    }

    public Venda buscarPorId(String id) throws Exception {
        try {
            return this.repository.pegarPorId(id)
                .orElseThrow(() -> new Exception("Venda não encontrada com ID: " + id));
        } catch (Exception e) {
            System.err.println("Erro ao buscar venda por id " + id + ", erro: " + e.getMessage());
            throw new Exception("Erro ao buscar venda por id " + id);
        }
    }
    
    public void atualizar(String id, Venda venda) throws Exception {
        try {
            this.repository.atualizar(id, venda);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao atualizar venda: " + e.getMessage());
            throw new Exception("Erro ao atualizar venda com ID: " + id);
        } catch (Exception e) {
            System.err.println("Erro inesperado ao atualizar venda: " + e.getMessage());
            throw new Exception("Erro inesperado ao atualizar venda com ID: " + id);
        }
    }
}
