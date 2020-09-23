package ar.com.ada.api.cursos.models.request;

import java.util.Date;

import ar.com.ada.api.cursos.entities.Pais.TipoDocuEnum;
import ar.com.ada.api.cursos.entities.Usuario.TipoUsuarioEnum;

import javax.validation.constraints.*;

/**
 * RegistrationRequest
 */
public class RegistrationRequest {

    @NotBlank(message = "el nombre no puede ser nulo")
    public String fullName; // Nombre persona
    public Integer country; // pais del usuario
    @NotNull(message = "El tipo de usuario no puede ser nulo")
    public TipoDocuEnum identificationType; // Tipo Documento
    public String identification; // nro documento
    public Date birthDate; // fechaNacimiento
    public TipoUsuarioEnum userType;
    public String email; // email
    @NotBlank(message = "La contraseña debe tener al menos 8 digitos")
    public String password; // contraseña elegida por el usuario.

}