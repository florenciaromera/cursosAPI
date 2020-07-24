package ar.com.ada.api.cursos.entities;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "clase")
public class Clase {
    @Id
    @Column(name = "clase_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer claseId;
    private Integer numero;
    @ManyToOne
    @JoinColumn(name = "curso_id", referencedColumnName = "curso_id")
    private Curso curso;
    private String titulo;
    @Column(name = "duracion_horas")
    private Integer duracionHoras;
    @OneToMany(mappedBy = "clase", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Contenido> contenidos = new ArrayList<>();;

    public Integer getClaseId() {
        return claseId;
    }

    public void setClaseId(Integer claseId) {
        this.claseId = claseId;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(Integer duracionHoras) {
        this.duracionHoras = duracionHoras;
    }

    public List<Contenido> getContenidos() {
        return contenidos;
    }

    public void setContenidos(List<Contenido> contenidos) {
        this.contenidos = contenidos;
    }

}