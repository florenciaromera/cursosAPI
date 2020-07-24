package ar.com.ada.api.cursos.models.response;

import java.util.List;

import ar.com.ada.api.cursos.entities.Curso;

public class CategoriaResponse {
    public Integer categoriaId;
    public String nombre;
    public String descripcion;
    public List<Curso> cursosLista;
}