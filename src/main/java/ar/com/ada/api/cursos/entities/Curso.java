package ar.com.ada.api.cursos.entities;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "curso")
public class Curso {
    @Id
    @Column(name = "curso_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cursoId;
    private String nombre;
    // cursosQueDicta para diferenciar los cursos de docente de los de estudiante
    @ManyToMany(mappedBy = "cursosQueDicta")
    private List<Docente> docentes;
    @ManyToMany(mappedBy = "cursosQueAsiste")
    private List<Estudiante> estudiantes;
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Clase> clases;
    @ManyToMany(mappedBy = "cursos")
    private List<Categoria> categorias;
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Inscripcion> inscripciones;

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Docente> getDocentes() {
        return docentes;
    }

    public void setDocentes(List<Docente> docentes) {
        this.docentes = docentes;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public List<Clase> getClases() {
        return clases;
    }

    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

}