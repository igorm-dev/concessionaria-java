package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Venda;

public class VendaRepository implements RepositoryBase<Venda> {
    public static final List<Venda> vendas = new ArrayList<>();

    @Override
    public void salvar(Venda entity) {
        vendas.add(entity);
    }

    @Override
    public void deletar(String id) {
        vendas.removeIf((venda) -> venda.getId().equals(id));
    }

    @Override
    public List<Venda> encontrarTodos() {
        return vendas;
    }

    @Override
    public Optional<Venda> pegarPorId(String id) {
        return vendas.stream().filter(venda -> venda.getId().equals(id)).findFirst();
    }

    @Override
    public void atualizar(String id, Venda entity) {
        Optional<Venda> optionalVenda = this.pegarPorId(id);
        
        if (optionalVenda.isPresent()) {
            Venda venda = optionalVenda.get();
            venda.setCliente(entity.getCliente());
            venda.setVeiculo(entity.getVeiculo());
            venda.setMetodoPagamento(entity.getMetodoPagamento());
            venda.setValorTotal(entity.getValorTotal());
        } else {
            throw new IllegalArgumentException("Venda com ID " + id + " não encontrada.");
        }
        
    }
}
