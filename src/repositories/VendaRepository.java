package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import model.Venda;
import utils.Desserializador;
import utils.Serilizador;

public class VendaRepository implements RepositoryBase<Venda> {
    private final Serilizador<Venda> serilizador = new Serilizador<>();
    private  final Desserializador<Venda> desserializador = new Desserializador<>();

    private final String caminhoArquivo = "vendas.ser";

    private final String UUID_PREFIX = "ven-";

    @Override
    public String criarUUID() {
        return UUID_PREFIX + UUID.randomUUID().toString();
    }

    @Override
    public void salvar(Venda entity) {
        try {
            entity.setId(this.criarUUID());
    
            List<Venda> vendas = this.encontrarTodos();
            vendas.add(entity);
    
            serilizador.salvar(vendas, this.caminhoArquivo);
        } catch (Exception e) {
            System.err.println("Erro ao salvar venda: " + e.getMessage());
            throw new RuntimeException("Erro ao salvar venda", e);
        }
    }

    @Override
    public void deletar(String id) {
        try {
            List<Venda> vendas = this.desserializador.carregar(this.caminhoArquivo);
            vendas.removeIf((venda) -> venda.getId().equals(id));
            serilizador.salvar(vendas, this.caminhoArquivo);
        } catch (Exception e) {
            System.err.println("Erro ao deletar venda: " + e.getMessage());
            throw new RuntimeException("Erro ao deletar venda", e);
        }
    }

    @Override
    public List<Venda> encontrarTodos() throws Exception {
        try {
            List<Venda> vendas = this.desserializador.carregar(this.caminhoArquivo);

            if (vendas == null) {
                return new ArrayList<>();
            }

            return vendas;
        } catch (Exception e) {
            System.err.println("Erro ao carregar vendas: " + e.getMessage());
            throw new Exception("Erro ao carregar vendas");
        }
    }

    @Override
    public Optional<Venda> pegarPorId(String id) {
        try {
            List<Venda> vendas = this.desserializador.carregar(this.caminhoArquivo);
            return vendas.stream().filter(venda -> venda.getId().equals(id)).findFirst();
        } catch (Exception e) {
            System.err.println("Erro ao buscar venda: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar venda");
        }
    }

    @Override
    public void atualizar(String id, Venda entity) {
        try {
            List<Venda> vendas = this.desserializador.carregar(this.caminhoArquivo);
            
            Optional<Venda> optionalVenda = vendas.stream().filter(venda -> venda.getId().equals(id)).findFirst();

            if (optionalVenda.isPresent()) {
                Venda venda = optionalVenda.get();
                venda.setCliente(entity.getCliente());
                venda.setVeiculo(entity.getVeiculo());
                venda.setMetodoPagamento(entity.getMetodoPagamento());
                venda.setValorTotal(entity.getValorTotal());

                serilizador.salvar(vendas, this.caminhoArquivo);
            } else {
                throw new IllegalArgumentException("Venda com ID " + id + " não encontrada.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao atualizar venda: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar venda");
        }
    }
}
