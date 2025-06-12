package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class Serilizador<T> {
    public void salvar(List<T> entity, String caminhoArquivo) {
        try (FileOutputStream fos = new FileOutputStream(caminhoArquivo); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(entity);
        } catch (IOException e) {
            System.err.println("Erro ao salvar as entidades: " + e.getMessage());
            System.err.println(e);
        }
    }
}