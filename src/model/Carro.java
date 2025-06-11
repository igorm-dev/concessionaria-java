package model;

public final class Carro extends Veiculo {
    private int quantidadePortas;

    public Carro(String id, String marca, String modelo, String placa, String cor, int quantidadePortas) {
        super(id, marca, modelo, placa, cor);
        this.quantidadePortas = quantidadePortas;
    }

    public Carro(String marca, String modelo, String placa, String cor, int quantidadePortas) {
        super(marca, modelo, placa, cor);
        this.quantidadePortas = quantidadePortas;
    }

    public int getQuantidadePortas() {
        return quantidadePortas;
    }

    public void setQuantidadePortas(int quantidadePortas) {
        this.quantidadePortas = quantidadePortas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Carro {");
        sb.append("quantidadePortas=").append(quantidadePortas);
        sb.append('}');
        return sb.toString();
    }
}
