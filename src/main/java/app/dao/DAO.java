package app.dao;

import java.util.List;

public interface DAO<T> {
    void guardar(T entidad);
    T buscarPorId(int id);
    List<T> listarTodos();
    void eliminar(int id);
}

