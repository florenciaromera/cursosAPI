package ar.com.ada.api.cursos.models.response;

public class GenericResponse {
    public boolean isOk;
    public String message;
    public Integer id;

    public GenericResponse(){

    }

    public GenericResponse(boolean isOk, String message){
        this.isOk = isOk;
        this.message = message;
    }

    public GenericResponse(boolean isOk, String message, Integer id){
        this.isOk = isOk;
        this.message = message;
        this.id = id;
    }
}