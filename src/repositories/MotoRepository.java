package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import model.Moto;
import utils.Desserializador;
import utils.Serilizador;

public class MotoRepository {
    private final String UUID_PREFIX = "mot-";

    private final String caminhoArquivo = "motos.ser";

    public String criarUUID() {
        return UUID_PREFIX + UUID.randomUUID().toString();
    }

    public void salvar(Moto entity) {
        try {
            entity.setId(this.criarUUID());
    
            List<Moto> motos = this.encontrarTodos();
            motos.add(entity);
    
            Serilizador.salvarMoto(motos, this.caminhoArquivo);
        } catch (Exception e) {
            System.err.println("Erro ao salvar moto: " + e.getMessage());
            throw new RuntimeException("Erro ao salvar moto", e);
        }
    }

    public void deletar(String id) {
        try {
            List<Moto> motos = Desserializador.carregarMoto(this.caminhoArquivo);
            motos.removeIf((moto) -> moto.getId().equals(id));
            Serilizador.salvarMoto(motos, this.caminhoArquivo);
        } catch (Exception e) {
            System.err.println("Erro ao deletar moto: " + e.getMessage());
            throw new RuntimeException("Erro ao deletar moto", e);
        }
    }

    public List<Moto> encontrarTodos() throws Exception {
        try {
            List<Moto> motos = Desserializador.carregarMoto(this.caminhoArquivo);

            if (motos == null) {
                return new ArrayList<>();
            }

            return motos;
        } catch (Exception e) {
            System.err.println("Erro ao carregar moto: " + e.getMessage());
            throw new Exception("Erro ao carregar moto");
        }
    }

    public Optional<Moto> pegarPorId(String id) {
        try {
            List<Moto> motos = Desserializador.carregarMoto(this.caminhoArquivo);
            return motos.stream().filter(moto -> moto.getId().equals(id)).findFirst();
        } catch (Exception e) {
            System.err.println("Erro ao buscar moto: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar moto");
        }
    }

    public void atualizar(String id, Moto entity) {
        try {
            List<Moto> motos = Desserializador.carregarMoto(this.caminhoArquivo);
            
            Optional<Moto> optionalMotos = motos.stream().filter(moto -> moto.getId().equals(id)).findFirst();

            if (optionalMotos.isPresent()) {
                Moto moto = optionalMotos.get();
                moto.setMarca(entity.getMarca());
                moto.setModelo(entity.getModelo());
                moto.setCor(entity.getCor());
                moto.setTemDescansoLateral(entity.getTemDescansoLateral());

                Serilizador.salvarMoto(motos, this.caminhoArquivo);
            } else {
                throw new IllegalArgumentException("moto com ID " + id + " não encontrada.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao atualizar moto: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar moto");
        }
    }
}
