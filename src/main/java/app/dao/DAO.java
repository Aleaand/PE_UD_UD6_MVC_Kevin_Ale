package app.dao;

import java.util.List;

public interface DAO<T> {
    void guardar(T entidad);
    T buscarPorId(int id);
    List<T> listarTodos();
    String eliminar(int id);
    String actualizar(T entidad);
}

