package controllers;

import java.util.List;
import model.Carro;
import repositories.CarroRepository;

public class CarroController {
    private final CarroRepository repository;

    public CarroController(CarroRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(Carro carro) throws Exception {
        try {
            this.repository.salvar(carro);
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar carro: " + e.getMessage());
            throw new Exception("Erro ao cadastrar moto");
        }
    }

    public void deletar(String id) throws Exception {
        try {
            this.repository.deletar(id);
        } catch (Exception e) {
            System.err.println("Erro ao deletar carro: " + e.getMessage());
            throw new Exception("Erro ao deletar carro com ID: " + id);
        }
    }

    public List<Carro> listar() throws Exception {
        try {
            return this.repository.encontrarTodos();
        } catch (Exception e) {
            System.err.println("Erro ao listar carros: " + e.getMessage());
            throw new Exception("Erro ao listar carros");
        }
    }

    public Carro buscarPorId(String id) throws Exception {
        try {
            return this.repository.pegarPorId(id).orElseThrow(() -> new Exception("Carro não encontrado com ID: " + id));
        } catch (Exception e) {
            System.err.println("Erro ao buscar carro por id " + id + ", erro: " + e.getMessage());
            throw new Exception("Erro ao buscar carro por id " + id);
        }
    }

    public void atualizar(String id, Carro carro) throws Exception {
        try {
            this.repository.atualizar(id, carro);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao atualizar carro: " + e.getMessage());
            throw new Exception("Erro ao atualizar carro com ID: " + id);
        } catch (Exception e) {
            System.err.println("Erro inesperado ao atualizar carro: " + e.getMessage());
            throw new Exception("Erro inesperado ao atualizar carro com ID: " + id);
        }
    }
}
