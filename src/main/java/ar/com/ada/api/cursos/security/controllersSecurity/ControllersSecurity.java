package ar.com.ada.api.cursos.security.controllersSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import ar.com.ada.api.cursos.entities.Usuario;
import ar.com.ada.api.cursos.services.UsuarioService;

@Component
public class ControllersSecurity {
    @Autowired
    UsuarioService usuarioService;

    public boolean isStaff(User user) {
        if (usuarioService.buscarPorUsername(user.getUsername()).getTipoUsuarioId().getValue() == 3) {
            return true;
        }
        return false;
    }

    public boolean isEstudiante(User user, Integer estudianteId) {
        Usuario usuarioEstudiante = usuarioService.buscarPorUsername(user.getUsername());
        if ((usuarioEstudiante.getTipoUsuarioId().getValue() == 2)
                && estudianteId.equals(usuarioEstudiante.getEstudiante().getEstudianteId())) {
            System.out.println("Entró por acá");
            return true;
        }
        return false;
    }

    public boolean isDocente(User user, Integer docenteId) {
        Usuario usuarioDocente = usuarioService.buscarPorUsername(user.getUsername());
        if ((usuarioDocente.getTipoUsuarioId().getValue() == 1)
                && docenteId.equals(usuarioDocente.getDocente().getDocenteId())) {
            return true;
        }
        return false;
    }

}