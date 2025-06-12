package model;

import java.io.Serializable;

public final class Moto extends Veiculo implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean temDescansoLateral;
    
    public Moto(String id, String marca, String modelo, String placa, String cor, boolean temDescansoLateral) {
        super(id, marca, modelo, placa, cor);
        this.temDescansoLateral = temDescansoLateral;
    }

    public Moto(String marca, String modelo, String placa, String cor, boolean temDescansoLateral) {
        super(marca, modelo, placa, cor);
        this.temDescansoLateral = temDescansoLateral;
    }

    public boolean getTemDescansoLateral() {
        return temDescansoLateral;
    }

    public void setTemDescansoLateral(boolean temDescansoLateral) {
        this.temDescansoLateral = temDescansoLateral;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nMoto {");
        sb.append("\n\tid: ").append(this.id);
        sb.append("\n\tmarca: ").append(this.marca);
        sb.append("\n\tmodelo: ").append(this.modelo);
        sb.append("\n\tplaca: ").append(this.placa);
        sb.append("\n\tcor: ").append(this.cor);
        sb.append("\n\ttemDescansoLateral=").append(temDescansoLateral);
        sb.append("\n}\n");
        return sb.toString();
    }
}
