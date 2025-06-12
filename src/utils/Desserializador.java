package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class Desserializador<T> {
    @SuppressWarnings("unchecked")
    public List<T> carregar(String caminhoArquivo) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(caminhoArquivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            return (List<T>) ois.readObject();
        } catch (IOException|ClassNotFoundException e) {
            System.err.println("Erro: " + e.getMessage());
            return null;
        }
    }
}