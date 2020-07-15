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
    // cursosDictados para diferenciar los cursos de docente de los de estudiante
    @ManyToMany(mappedBy = "cursosQueDicta")
    private List<Docente> docentes;
    @ManyToMany(mappedBy = "cursosQueAsiste")
    private List<Estudiante> estudiantes;
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Clase> clases;
    @ManyToMany(mappedBy = "cursos")
    private List<Categoria> categorias;
}