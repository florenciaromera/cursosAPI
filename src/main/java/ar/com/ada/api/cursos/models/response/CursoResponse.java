package ar.com.ada.api.cursos.models.response;

import java.util.List;

import ar.com.ada.api.cursos.entities.*;

public class CursoResponse {
    public Integer cursoId;
    public String nombre;
    public String descripcion;
    public Integer duracionHoras;
    public List<Docente> docentesLista;
    public List<Estudiante> estudiantesLista;
    public List<Clase> clasesLista;
    public List<Categoria> categoriasLista;
    public List<Inscripcion> inscripcionesLista;
}