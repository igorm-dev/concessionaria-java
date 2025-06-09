package factories;

import model.Carro;
import model.Moto;

public abstract class VeiculoFactory {
    public static Carro criarCarro(String marca, String modelo, String cor, double preco, int quantidadePortas) {
        return new Carro(marca, modelo, cor, preco, quantidadePortas);
    }

    public static Moto criarMoto(String id, String marca, String modelo, String cor, double preco, boolean temDescansoLateral) {
        return new Moto(marca, modelo, cor, preco, temDescansoLateral);
    }
}
