package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import model.Moto;

public class MotoRepository implements RepositoryBase<Moto> {
    public static final List<Moto> motos = new ArrayList<>();

    private final String UUID_PREFIX = "mot-";

    @Override
    public String criarUUID() {
        return UUID_PREFIX + UUID.randomUUID().toString();
    }

    @Override
    public void salvar(Moto entity) {
        motos.add(entity);
    }

    @Override
    public void deletar(String id) {
        motos.removeIf((moto) -> moto.getId().equals(id));
    }

    @Override
    public List<Moto> encontrarTodos() {
        return motos;
    }

    @Override
    public Optional<Moto> pegarPorId(String id) {
        return motos.stream().filter(moto -> moto.getId().equals(id)).findFirst();
    }

    @Override
    public void atualizar(String id, Moto entity) {
        Optional<Moto> optionalVenda = this.pegarPorId(id);
        
        if (optionalVenda.isPresent()) {
            Moto moto = optionalVenda.get();
            moto.setMarca(entity.getMarca());
            moto.setModelo(entity.getModelo());
            moto.setCor(entity.getCor());
            moto.setTemDescansoLateral(entity.getTemDescansoLateral());
        } else {
            throw new IllegalArgumentException("Moto com ID " + id + " não encontrada.");
        }
        
    }
}
