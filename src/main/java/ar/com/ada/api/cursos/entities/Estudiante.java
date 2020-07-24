package ar.com.ada.api.cursos.entities;

import java.util.*;

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
    private List<Curso> cursosQueAsiste = new ArrayList<>();
    @OneToOne(mappedBy = "estudiante") // nombre del atributo en el obj usuario
    private Usuario usuario;

    public Integer getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Integer estudianteId) {
        this.estudianteId = estudianteId;
    }

    public List<Curso> getCursosQueAsiste() {
        return cursosQueAsiste;
    }

    public void setCursosQueAsiste(List<Curso> cursosQueAsiste) {
        this.cursosQueAsiste = cursosQueAsiste;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
