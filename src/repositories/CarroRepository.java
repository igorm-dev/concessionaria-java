package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import model.Carro;
import utils.Desserializador;
import utils.Serilizador;

public class CarroRepository {
    private final String UUID_PREFIX = "car-";

    private final String CAMINHO_ARQUIVO = "carros.ser";

    public String criarUUID() {
        return UUID_PREFIX + UUID.randomUUID().toString();
    }

    public void salvar(Carro entity) {
        try {
            entity.setId(this.criarUUID());
    
            List<Carro> carros = this.encontrarTodos();
            carros.add(entity);
    
            Serilizador.salvarCarro(carros, this.CAMINHO_ARQUIVO);
        } catch (Exception e) {
            System.err.println("Erro ao salvar carro: " + e.getMessage());
            throw new RuntimeException("Erro ao salvar carro", e);
        }
    }

    public void deletar(String id) {
        try {
            List<Carro> carros = Desserializador.carregarCarro(this.CAMINHO_ARQUIVO);
            carros.removeIf((carro) -> carro.getId().equals(id));
            Serilizador.salvarCarro(carros, this.CAMINHO_ARQUIVO);
        } catch (Exception e) {
            System.err.println("Erro ao deletar carro: " + e.getMessage());
            throw new RuntimeException("Erro ao deletar carro", e);
        }
    }

    public List<Carro> encontrarTodos() throws Exception {
        try {
            List<Carro> carrosAtuais = Desserializador.carregarCarro(this.CAMINHO_ARQUIVO);

            if (carrosAtuais == null) {
                return new ArrayList<>();
            }

            return carrosAtuais;
        } catch (Exception e) {
            System.err.println("Erro ao carregar carros: " + e.getMessage());
            throw new Exception("Erro ao carregar carros");
        }
    }

    public Optional<Carro> pegarPorId(String id) {
        try {
            List<Carro> carros = Desserializador.carregarCarro(this.CAMINHO_ARQUIVO);
            return carros.stream().filter(carro -> carro.getId().equals(id)).findFirst();
        } catch (Exception e) {
            System.err.println("Erro ao buscar carro: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar carro");
        }
    }

    public void atualizar(String id, Carro entity) {
        try {
            List<Carro> carros = Desserializador.carregarCarro(this.CAMINHO_ARQUIVO);
            
            Optional<Carro> optionalCarro = carros.stream().filter(carro -> carro.getId().equals(id)).findFirst();

            if (optionalCarro.isPresent()) {
                Carro carro = optionalCarro.get();
                carro.setMarca(entity.getMarca());
                carro.setModelo(entity.getModelo());
                carro.setCor(entity.getCor());
                carro.setQuantidadePortas(entity.getQuantidadePortas());

                Serilizador.salvarCarro(carros, this.CAMINHO_ARQUIVO);
            } else {
                throw new IllegalArgumentException("Carro com ID " + id + " não encontrada.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao atualizar carro: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar carro");
        }
    }
}
