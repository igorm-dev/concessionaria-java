package controllers;

import factories.VeiculoFactory;
import java.util.ArrayList;
import java.util.List;
import model.Carro;
import repositories.CarroRepository;

public class CarroController {
    private final CarroRepository repository = new CarroRepository();

    public void cadastrar(String id, String marca, String modelo, String cor, double preco, int numeroPortas) {
        try {
            Carro carro = VeiculoFactory.criarCarro(marca, modelo, cor, preco, numeroPortas);
            repository.salvar(carro);
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar carro: " + e.getMessage());
        }
    }

    public void deletar(String id) {
        try {
            repository.deletar(id);
        } catch (Exception e) {
            System.err.println("Erro ao deletar carro: " + e.getMessage());
        }
    }

    public List<Carro> listar() {
        try {
            return repository.encontrarTodos();
        } catch (Exception e) {
            System.err.println("Erro ao listar carros: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public Carro buscarPorId(String id) throws Exception {
        try {
            return repository.pegarPorId(id).orElseThrow(() -> new Exception("Carro não encontrado com ID: " + id));
        } catch (Exception e) {
            System.err.println("Erro ao buscar carro por id " + id + ", erro: " + e.getMessage());
            throw new Exception("Erro ao buscar carro por id " + id);
        }
    }

    public void atualizar(String id, String marca, String modelo, String cor, double preco, int numeroPortas) throws Exception {
        try {
            Carro carro = VeiculoFactory.criarCarro(id, marca, modelo, cor, preco, numeroPortas);
            repository.atualizar(id, carro);
        } catch (Exception e) {
            System.err.println("Erro ao atualizar carro: " + e.getMessage());
            throw new Exception("Erro ao atualizar carro com id: " + id);
        }
    }
}
