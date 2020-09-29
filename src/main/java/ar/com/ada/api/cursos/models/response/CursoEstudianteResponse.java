package ar.com.ada.api.cursos.models.response;

import java.util.*;

import ar.com.ada.api.cursos.entities.Categoria;

public class CursoEstudianteResponse {
    public Integer cursoId;
    public String nombre;
    public String descripcion;
    public List<Categoria> categorias = new ArrayList<>();
    public List<DocenteSimplificadoResponse> docentes = new ArrayList<>();
    public Integer duracionHoras;

    public CursoEstudianteResponse(){

    }

    public CursoEstudianteResponse(Integer id, String nombre, String descripcion, Integer duracionHoras, List<Categoria> categorias){
        this.cursoId = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracionHoras = duracionHoras;
        this.categorias = categorias;
    }
}