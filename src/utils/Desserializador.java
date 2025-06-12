package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import model.Carro;
import model.Cliente;
import model.Moto;
import model.Venda;

public abstract class Desserializador {
    @SuppressWarnings("unchecked")
    public static List<Carro> carregarCarro(String caminhoArquivo) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(caminhoArquivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            return (List<Carro>) ois.readObject();
        } catch (IOException|ClassNotFoundException e) {
            System.err.println("Erro: " + e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Moto> carregarMoto(String caminhoArquivo) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(caminhoArquivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            return (List<Moto>) ois.readObject();
        } catch (IOException|ClassNotFoundException e) {
            System.err.println("Erro: " + e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Cliente> carregarCliente(String caminhoArquivo) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(caminhoArquivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            return (List<Cliente>) ois.readObject();
        } catch (IOException|ClassNotFoundException e) {
            System.err.println("Erro: " + e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Venda> carregarVenda(String caminhoArquivo) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(caminhoArquivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            return (List<Venda>) ois.readObject();
        } catch (IOException|ClassNotFoundException e) {
            System.err.println("Erro: " + e.getMessage());
            return null;
        }
    }
}