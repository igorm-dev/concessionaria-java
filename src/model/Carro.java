package model;

import utils.Util;

public class Carro extends Veiculo {
    private static final String UUID_PREFIX = "car-";

    private int quantidadePortas;

    public Carro(String marca, String modelo, String cor, double preco, int quantidadePortas) {
        super(Util.criarUUID(UUID_PREFIX), marca, modelo, cor, preco);

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
