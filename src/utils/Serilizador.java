package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serilizador<T> {
    public void salvar(T entity, String caminhoArquivo) {
        try (FileOutputStream fos = new FileOutputStream(caminhoArquivo); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(entity);
        } catch (IOException e) {
            System.err.println("Erro ao salvar a entidade: " + e.getMessage());
            System.err.println(e);
        }
    }
}