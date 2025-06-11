package repositories;

import java.util.List;
import java.util.Optional;

public interface RepositoryBase<T> {
    String criarUUID();

    void salvar(T entity);

    void deletar(String id);

    List<T> encontrarTodos();
    Optional<T> pegarPorId(String id);
    
    void atualizar(String id, T entity);
}
