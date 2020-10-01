package ar.com.ada.api.cursos.services.base;

import java.util.List;

public interface IService<T> {
    //crear
    boolean crear(T entity);
    //actualizar
    T actualizar(T entity);
    //buscarporid
    T buscarPorId(Integer id);
    //listarTodos
    List<T> listarTodas();
}
