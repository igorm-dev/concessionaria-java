package model;

import java.io.Serializable;

public final class Venda implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private Cliente cliente;
    private Veiculo veiculo;
    private MetodoPagamento metodoPagamento;
    private double valorTotal;

    public Venda(String id, Cliente cliente, Veiculo veiculo, MetodoPagamento metodoPagamento, double valorTotal) {
        this.id = id;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.metodoPagamento = metodoPagamento;
        this.valorTotal = valorTotal;
    }

    public Venda(Cliente cliente, Veiculo veiculo, MetodoPagamento metodoPagamento, double valorTotal) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.metodoPagamento = metodoPagamento;
        this.valorTotal = valorTotal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nVenda {");
        sb.append("\n\tid: ").append(this.id);
        sb.append("\n\tcliente: ").append(this.cliente);
        sb.append("\n\tveiculo: ").append(this.veiculo);
        sb.append("\n\tmetodoPagamentov: ").append(this.metodoPagamento);
        sb.append("\n\tvalorTotal: ").append(this.valorTotal);
        sb.append("\n}\n");
        return sb.toString();
    }
}
