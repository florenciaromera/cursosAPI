package ar.com.ada.api.cursos.models.response;

import ar.com.ada.api.cursos.entities.Usuario.TipoUsuarioEnum;

public class LoginResponse {
    public Integer id;
    public String username;
    public String token;
    public String email;
    public TipoUsuarioEnum userType;
    public Integer entityId; // Si es un Docente, va el Id de Docente, si es estudiante Id Estudiante

    public LoginResponse(){

    }

    public LoginResponse (Integer id, String username, String token, String email, TipoUsuarioEnum userType, Integer entityId){
        this.id = id;
        this.username = username;
        this.token = token;
        this.email = email;
        this.userType = userType;
        this.entityId = entityId;
    }
}