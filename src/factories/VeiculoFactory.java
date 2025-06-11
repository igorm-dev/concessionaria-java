package factories;

import model.Carro;
import model.Moto;

public abstract class VeiculoFactory {
    public static Carro criarCarroPersistido(String id, String marca, String modelo, String cor, int quantidadePortas) {
        return new Carro(id, marca, modelo, cor, quantidadePortas);
    }

    public static Carro criarNovoCarro(String marca, String modelo, String placa, String cor, int quantidadePortas) {
        return new Carro(marca, modelo, placa, cor, quantidadePortas);
    }

    public static Moto criarMotoPersistida(String id, String marca, String modelo, String cor, boolean temDescansoLateral) {
        return new Moto(id, marca, modelo, cor, temDescansoLateral);
    }

    public static Moto criarNovaMoto(String marca, String modelo, String placa, String cor, boolean temDescansoLateral) {
        return new Moto(marca, modelo, placa, cor, temDescansoLateral);
    }
}
