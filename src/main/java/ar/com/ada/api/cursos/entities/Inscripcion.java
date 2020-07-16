package ar.com.ada.api.cursos.entities;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "inscripcion")
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inscripcion_id")
    private Integer inscripcionId;
    @ManyToOne
    @JoinColumn(name = "curso_id", referencedColumnName = "curso_id")
    private Curso curso;
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    private Usuario usuario;
    @Column(name = "estado_inscripcion_id")
    private EstadoInscripcionEnum estadoInscripcionEnum;

    public enum EstadoInscripcionEnum {
        DOCENTE(1), ESTUDIANTE(2), STAFF(3);

        private final Integer value;

        // NOTE: Enum constructor tiene que estar en privado
        private EstadoInscripcionEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static EstadoInscripcionEnum parse(Integer id) {
            EstadoInscripcionEnum status = null; // Default
            for (EstadoInscripcionEnum item : EstadoInscripcionEnum.values()) {
                if (item.getValue().equals(id)) {
                    status = item;
                    break;
                }
            }
            return status;
        }
    }
}