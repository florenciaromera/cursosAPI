package ar.com.ada.api.cursos.entities;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Integer usuarioId;
    private String username;
    private String password;
    private String email;
    @Column(name = "fecha_login")
    private Date fechaLogin;
    @Column(name = "tipo_usuario_id")
    private TipoUsuarioEnum tipoUsuarioId;
    @OneToOne
    @JoinColumn(name = "estudiante_id", referencedColumnName = "estudiante_id")
    private Estudiante estudiante;
    @OneToOne
    @JoinColumn(name = "docente_id", referencedColumnName = "docente_id")
    private Docente docente;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Inscripcion> inscripciones = new ArrayList<>();

    public enum TipoUsuarioEnum {
        DOCENTE(1), ESTUDIANTE(2), STAFF(3);

        private final Integer value;

        // NOTE: Enum constructor tiene que estar en privado
        private TipoUsuarioEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static TipoUsuarioEnum parse(Integer id) {
            TipoUsuarioEnum status = null; // Default
            for (TipoUsuarioEnum item : TipoUsuarioEnum.values()) {
                if (item.getValue().equals(id)) {
                    status = item;
                    break;
                }
            }
            return status;
        }
    }
}