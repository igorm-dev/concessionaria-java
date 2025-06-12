package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import model.Carro;
import model.Cliente;
import model.Moto;
import model.Venda;

public abstract class Serilizador {
    public static void salvarCarro(List<Carro> entity, String caminhoArquivo) {
        try (FileOutputStream fos = new FileOutputStream(caminhoArquivo); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(entity);
        } catch (IOException e) {
            System.err.println("Erro ao salvar os carros: " + e.getMessage());
            System.err.println(e);
        }
    }

    public static void salvarMoto(List<Moto> entity, String caminhoArquivo) {
        try (FileOutputStream fos = new FileOutputStream(caminhoArquivo); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(entity);
        } catch (IOException e) {
            System.err.println("Erro ao salvar os motos: " + e.getMessage());
            System.err.println(e);
        }
    }

    public static void salvarCliente(List<Cliente> entity, String caminhoArquivo) {
        try (FileOutputStream fos = new FileOutputStream(caminhoArquivo); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(entity);
        } catch (IOException e) {
            System.err.println("Erro ao salvar os clientes: " + e.getMessage());
            System.err.println(e);
        }
    }

    public static void salvarVenda(List<Venda> entity, String caminhoArquivo) {
        try (FileOutputStream fos = new FileOutputStream(caminhoArquivo); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(entity);
        } catch (IOException e) {
            System.err.println("Erro ao salvar as vendas: " + e.getMessage());
            System.err.println(e);
        }
    }
}