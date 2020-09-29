package ar.com.ada.api.cursos.models.response;

public class DocenteSimplificadoResponse {
    public Integer docenteId;
    public String nombre;

    public DocenteSimplificadoResponse(){

    }

    public DocenteSimplificadoResponse(Integer docenteId, String nombre){
        this.docenteId = docenteId;
        this.nombre = nombre;
    }
}