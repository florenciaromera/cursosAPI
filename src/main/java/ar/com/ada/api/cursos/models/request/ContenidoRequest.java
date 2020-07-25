package ar.com.ada.api.cursos.models.request;

import ar.com.ada.api.cursos.entities.Contenido.TipoContenidoEnum;

public class ContenidoRequest {
    public String descripcion;
    public String descripcionLarga;
    public String payload;
    public String payloadSimple;
    public TipoContenidoEnum tipoContenidoId;
}