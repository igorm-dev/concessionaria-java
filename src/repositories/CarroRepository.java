package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import model.Carro;

public class CarroRepository implements RepositoryBase<Carro> {
    public static final List<Carro> carros = new ArrayList<>();

    private final String UUID_PREFIX = "car-";

    @Override
    public String criarUUID() {
        return UUID_PREFIX + UUID.randomUUID().toString();
    }

    @Override
    public void salvar(Carro entity) {
        entity.setId(this.criarUUID());

         


        carros.add(entity);
    }

    @Override
    public void deletar(String id) {
        carros.removeIf((carro) -> carro.getId().equals(id));
    }

    @Override
    public List<Carro> encontrarTodos() {
        return carros;
    }

    @Override
    public Optional<Carro> pegarPorId(String id) {
        return carros.stream().filter(carro -> carro.getId().equals(id)).findFirst();
    }

    @Override
    public void atualizar(String id, Carro entity) {
        Optional<Carro> optionalVenda = this.pegarPorId(id);
        
        if (optionalVenda.isPresent()) {
            Carro carro = optionalVenda.get();
            carro.setMarca(entity.getMarca());
            carro.setModelo(entity.getModelo());
            carro.setCor(entity.getCor());
            carro.setQuantidadePortas(entity.getQuantidadePortas());
        } else {
            throw new IllegalArgumentException("Carro com ID " + id + " não encontrada.");
        }
    }
}
