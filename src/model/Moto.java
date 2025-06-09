package model;

import utils.Util;

public class Moto extends Veiculo {
    private static final String UUID_PREFIX = "mot-";

    private boolean temDescansoLateral;
    
    public Moto(String marca, String modelo, String cor, double preco, boolean temDescansoLateral) {
        super(Util.criarUUID(UUID_PREFIX), marca, modelo, cor, preco);

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
