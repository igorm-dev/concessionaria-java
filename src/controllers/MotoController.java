package controllers;

import model.Moto;
import repositories.MotoRepository;

public class MotoController {
    private final MotoRepository repository;

    public MotoController(MotoRepository repository) {
        this.repository = repository;
    }
    
    public void cadastrar(Moto moto) throws Exception {
        try {
            this.repository.salvar(moto);
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar moto: " + e.getMessage());
            throw new Exception("Erro ao cadastrar moto");
        }
    }

    public void deletar(String id) throws Exception {
        try {
            this.repository.deletar(id);
        } catch (Exception e) {
            System.err.println("Erro ao deletar moto: " + e.getMessage());
            throw new Exception("Erro ao deletar moto com ID: " + id);
        }
    }

    public java.util.List<Moto> listar() throws Exception {
        try {
            return this.repository.encontrarTodos();
        } catch (Exception e) {
            System.err.println("Erro ao listar motos: " + e.getMessage());
            throw new Exception("Erro ao listar motos");
        }
    }
    
    public Moto buscarPorId(String id) throws Exception {
        try {
            return this.repository.pegarPorId(id)
                .orElseThrow(() -> new Exception("Moto não encontrada com ID: " + id));
        } catch (Exception e) {
            System.err.println("Erro ao buscar moto por id " + id + ", erro: " + e.getMessage());
            throw new Exception("Erro ao buscar moto por id " + id);
        }
    }

    public void atualizar(String id, Moto moto) throws Exception {
        try {
            this.repository.atualizar(id, moto);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao atualizar moto: " + e.getMessage());
            throw new Exception("Erro ao atualizar moto com ID: " + id);
        } catch (Exception e) {
            System.err.println("Erro inesperado ao atualizar moto: " + e.getMessage());
            throw new Exception("Erro inesperado ao atualizar moto com ID: " + id);
        }
    }

}
