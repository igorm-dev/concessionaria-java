package model;

public final class Moto extends Veiculo {
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
        sb.append("Moto{");
        sb.append("temDescansoLateral=").append(temDescansoLateral);
        sb.append('}');
        return sb.toString();
    }
}
