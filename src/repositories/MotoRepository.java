package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import model.Moto;
import utils.Desserializador;
import utils.Serilizador;

public class MotoRepository implements RepositoryBase<Moto> {
    private final Serilizador<Moto> serilizador = new Serilizador<>();
    private  final Desserializador<Moto> desserializador = new Desserializador<>();

    private final String UUID_PREFIX = "mot-";

    private final String caminhoArquivo = "motos.ser";

    @Override
    public String criarUUID() {
        return UUID_PREFIX + UUID.randomUUID().toString();
    }

    @Override
    public void salvar(Moto entity) {
        try {
            entity.setId(this.criarUUID());
    
            List<Moto> motos = this.encontrarTodos();
            motos.add(entity);
    
            serilizador.salvar(motos, this.caminhoArquivo);
        } catch (Exception e) {
            System.err.println("Erro ao salvar moto: " + e.getMessage());
            throw new RuntimeException("Erro ao salvar moto", e);
        }
    }

    @Override
    public void deletar(String id) {
        try {
            List<Moto> motos = this.desserializador.carregar(this.caminhoArquivo);
            motos.removeIf((moto) -> moto.getId().equals(id));
            serilizador.salvar(motos, this.caminhoArquivo);
        } catch (Exception e) {
            System.err.println("Erro ao deletar moto: " + e.getMessage());
            throw new RuntimeException("Erro ao deletar moto", e);
        }
    }

    @Override
    public List<Moto> encontrarTodos() throws Exception {
        try {
            List<Moto> motos = this.desserializador.carregar(this.caminhoArquivo);

            if (motos == null) {
                return new ArrayList<>();
            }

            return motos;
        } catch (Exception e) {
            System.err.println("Erro ao carregar moto: " + e.getMessage());
            throw new Exception("Erro ao carregar moto");
        }
    }

    @Override
    public Optional<Moto> pegarPorId(String id) {
        try {
            List<Moto> motos = this.desserializador.carregar(this.caminhoArquivo);
            return motos.stream().filter(moto -> moto.getId().equals(id)).findFirst();
        } catch (Exception e) {
            System.err.println("Erro ao buscar moto: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar moto");
        }
    }

    @Override
    public void atualizar(String id, Moto entity) {
        try {
            List<Moto> motos = this.desserializador.carregar(this.caminhoArquivo);
            
            Optional<Moto> optionalMotos = motos.stream().filter(moto -> moto.getId().equals(id)).findFirst();

            if (optionalMotos.isPresent()) {
                Moto moto = optionalMotos.get();
                moto.setMarca(entity.getMarca());
                moto.setModelo(entity.getModelo());
                moto.setCor(entity.getCor());
                moto.setTemDescansoLateral(entity.getTemDescansoLateral());

                serilizador.salvar(motos, this.caminhoArquivo);
            } else {
                throw new IllegalArgumentException("moto com ID " + id + " não encontrada.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao atualizar moto: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar moto");
        }
    }
}
