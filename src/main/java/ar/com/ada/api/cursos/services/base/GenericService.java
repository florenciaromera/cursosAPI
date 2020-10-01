package ar.com.ada.api.cursos.services.base;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class GenericService<T> implements IService<T> {

    @Autowired
    protected JpaRepository<T,Integer> repo;

    @Override
    public boolean crear(T entity) {
        repo.save(entity);
        return true;
    }

    @Override
    public T actualizar(T entity) {
        repo.save(entity);

        return entity;
    }

    @Override
    public T buscarPorId(Integer id) {
        
        Optional<T> opT = repo.findById(id);

        // Si tiene un valor de categoria en el elemento que trajo.
        // Camion con heladera dentro. hasta que no abrimos la puerta no sabemos si la
        // trajo.
        if (opT.isPresent())
            return opT.get();
        else
            return null;

    }

    @Override
    public List<T> listarTodas() {
        return repo.findAll();
    }
    
}
