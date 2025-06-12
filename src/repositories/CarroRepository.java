package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import model.Carro;
import utils.Desserializador;
import utils.Serilizador;

public class CarroRepository implements RepositoryBase<Carro> {
    private final String UUID_PREFIX = "car-";
    
    private final Serilizador<Carro> serilizador = new Serilizador<>();
    private  final Desserializador<Carro> desserializador = new Desserializador<>();

    private final String caminhoArquivo = "carros.ser";

    @Override
    public String criarUUID() {
        return UUID_PREFIX + UUID.randomUUID().toString();
    }

    @Override
    public void salvar(Carro entity) {
        try {
            entity.setId(this.criarUUID());
    
            List<Carro> carros = this.encontrarTodos();
            carros.add(entity);
    
            serilizador.salvar(carros, this.caminhoArquivo);
        } catch (Exception e) {
            System.err.println("Erro ao salvar carro: " + e.getMessage());
            throw new RuntimeException("Erro ao salvar carro", e);
        }
    }

    @Override
    public void deletar(String id) {
        try {
            List<Carro> carros = this.desserializador.carregar(this.caminhoArquivo);
            carros.removeIf((carro) -> carro.getId().equals(id));
            serilizador.salvar(carros, this.caminhoArquivo);
        } catch (Exception e) {
            System.err.println("Erro ao deletar carro: " + e.getMessage());
            throw new RuntimeException("Erro ao deletar carro", e);
        }
    }

    @Override
    public List<Carro> encontrarTodos() throws Exception {
        try {
            List<Carro> carrosAtuais = this.desserializador.carregar(this.caminhoArquivo);

            if (carrosAtuais == null) {
                return new ArrayList<>();
            }

            return carrosAtuais;
        } catch (Exception e) {
            System.err.println("Erro ao carregar carros: " + e.getMessage());
            throw new Exception("Erro ao carregar carros");
        }
    }

    @Override
    public Optional<Carro> pegarPorId(String id) {
        try {
            List<Carro> carros = this.desserializador.carregar(this.caminhoArquivo);
            return carros.stream().filter(carro -> carro.getId().equals(id)).findFirst();
        } catch (Exception e) {
            System.err.println("Erro ao buscar carro: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar carro");
        }
    }

    @Override
    public void atualizar(String id, Carro entity) {
        try {
            List<Carro> carros = this.desserializador.carregar(this.caminhoArquivo);
            
            Optional<Carro> optionalCarro = carros.stream().filter(carro -> carro.getId().equals(id)).findFirst();

            if (optionalCarro.isPresent()) {
                Carro carro = optionalCarro.get();
                carro.setMarca(entity.getMarca());
                carro.setModelo(entity.getModelo());
                carro.setCor(entity.getCor());
                carro.setQuantidadePortas(entity.getQuantidadePortas());

                serilizador.salvar(carros, this.caminhoArquivo);
            } else {
                throw new IllegalArgumentException("Carro com ID " + id + " não encontrada.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao atualizar carro: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar carro");
        }
    }
}
