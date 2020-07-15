package ar.com.ada.api.cursos.entities;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "estudiante")
public class Estudiante extends Persona {
    @Id
    @Column(name = "estudiante_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer estudianteId;
    @ManyToMany
    @JoinTable(name = "estudiante_x_curso", joinColumns = @JoinColumn(name = "estudiante_id"), inverseJoinColumns = @JoinColumn(name = "curso_id"))
    private List<Curso> cursosQueAsiste;
}
