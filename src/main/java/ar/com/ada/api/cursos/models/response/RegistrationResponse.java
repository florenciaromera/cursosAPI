package ar.com.ada.api.cursos.models.response;

import java.util.*;

/**
 * RegistrationResponse
 */
public class RegistrationResponse {

    public boolean isOk = false;
    public String message = "";
    public Integer userId;

    public List<ErrorResponseItem> errors = new ArrayList<>();

    public RegistrationResponse(){

    }

    public RegistrationResponse(boolean isOk, String message, Integer userId){
        this.isOk = isOk;
        this.message = message;
        this.userId = userId;
    }
}