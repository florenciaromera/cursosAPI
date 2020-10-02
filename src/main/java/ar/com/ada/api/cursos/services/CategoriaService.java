package ar.com.ada.api.cursos.services;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import ar.com.ada.api.cursos.entities.*;
import ar.com.ada.api.cursos.models.request.CategoriaModifRequest;
import ar.com.ada.api.cursos.repos.CategoriaRepository;
import ar.com.ada.api.cursos.services.base.GenericService;

import java.util.*;

@Service
public class CategoriaService extends GenericService<Categoria> {


    public Categoria crearCategoria(String nombre, String descripcion) {
        Categoria categoria = new Categoria();
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);
        this.crear(categoria);
        return categoria;
    }

    public List<Categoria> traerTodasConA() {
        return ((CategoriaRepository)repo).findCategoriasConA();
    }

}