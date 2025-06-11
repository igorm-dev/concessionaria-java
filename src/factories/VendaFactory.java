package factories;

import model.Cliente;
import model.MetodoPagamento;
import model.Veiculo;
import model.Venda;

public abstract class VendaFactory {
    public static Venda criarVendaPersistida(String id, Cliente cliente, Veiculo veiculo, MetodoPagamento metodoPagamento, double valorTotal) {
        return new Venda(id, cliente, veiculo, metodoPagamento, valorTotal);
    }

    public static Venda criarNovaVenda(Cliente cliente, Veiculo veiculo, MetodoPagamento metodoPagamento, double valorTotal) {
        return new Venda(cliente, veiculo, metodoPagamento, valorTotal);
    }
}
