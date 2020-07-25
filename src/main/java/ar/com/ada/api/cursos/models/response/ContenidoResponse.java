package ar.com.ada.api.cursos.models.response;

import java.util.List;

import ar.com.ada.api.cursos.entities.Clase;
import ar.com.ada.api.cursos.entities.Contenido.TipoContenidoEnum;

public class ContenidoResponse {
    public Integer contenidoId;
    public String descripcion;
    public String descripcionLarga;
    public String payload;
    public String payloadSimple;
    public List<TipoContenidoEnum> tipoContenidoEnum;
    public Clase clase;
}