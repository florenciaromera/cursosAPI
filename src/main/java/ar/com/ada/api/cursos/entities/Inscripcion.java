package ar.com.ada.api.cursos.entities;

import java.math.BigDecimal;
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
    private EstadoInscripcionEnum estadoInscripcion;

    @Column(name = "pagada_importe")
    private BigDecimal PagADA_importe;
    @Column(name = "pagada_moneda")
    private String PagADA_moneda;
    @Column(name = "pagada_forma_pago")
    private String PagADA_formaPago;
    @Column(name = "pagada_codigo_barra")
    private String PagADA_CodigoBarras;

    @Column(name = "pagada_servicio_id")
    private Integer PagADA_ServicioId;

    @Column(name = "pagada_pago_id")
    private Integer PagADA_PagoId;

    public enum EstadoInscripcionEnum {
        INACTIVO(0), ACTIVO(1);

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

    public Integer getInscripcionId() {
        return inscripcionId;
    }

    public void setInscripcionId(Integer inscripcionId) {
        this.inscripcionId = inscripcionId;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public EstadoInscripcionEnum getEstadoInscripcion() {
        return estadoInscripcion;
    }

    public void setEstadoInscripcion(EstadoInscripcionEnum estadoInscripcion) {
        this.estadoInscripcion = estadoInscripcion;
    }

    public BigDecimal getPagADA_importe() {
        return PagADA_importe;
    }

    public void setPagADA_importe(BigDecimal pagADA_importe) {
        PagADA_importe = pagADA_importe;
    }

    public String getPagADA_moneda() {
        return PagADA_moneda;
    }

    public void setPagADA_moneda(String pagADA_moneda) {
        PagADA_moneda = pagADA_moneda;
    }

    public String getPagADA_formaPago() {
        return PagADA_formaPago;
    }

    public void setPagADA_formaPago(String pagADA_formaPago) {
        PagADA_formaPago = pagADA_formaPago;
    }

    public String getPagADA_CodigoBarras() {
        return PagADA_CodigoBarras;
    }

    public void setPagADA_CodigoBarras(String pagADA_CodigoBarras) {
        PagADA_CodigoBarras = pagADA_CodigoBarras;
    }

    public Integer getPagADA_ServicioId() {
        return PagADA_ServicioId;
    }

    public void setPagADA_ServicioId(Integer pagADA_ServicioId) {
        PagADA_ServicioId = pagADA_ServicioId;
    }

	public Integer getPagADA_PagoId() {
		return PagADA_PagoId;
	}

	public void setPagADA_PagoId(Integer pagADA_PagoId) {
		PagADA_PagoId = pagADA_PagoId;
	}

}  
