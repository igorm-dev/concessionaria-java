package model;

public final class Carro extends Veiculo {
    private static final long serialVersionUID = 1L;

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
        sb.append("\nCarro {");
        sb.append("\n\tid: ").append(this.id);
        sb.append("\n\tmarca: ").append(this.marca);
        sb.append("\n\tmodelo: ").append(this.modelo);
        sb.append("\n\tplaca: ").append(this.placa);
        sb.append("\n\tcor: ").append(this.cor);
        sb.append("\n\tquantidadePortas: ").append(this.quantidadePortas);
        sb.append("\n}\n");
        return sb.toString();
    }
}
