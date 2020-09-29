package ar.com.ada.api.cursos.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.cursos.entities.Usuario;
import ar.com.ada.api.cursos.models.request.*;
import ar.com.ada.api.cursos.models.response.*;
import ar.com.ada.api.cursos.security.jwt.JWTTokenUtil;
import ar.com.ada.api.cursos.services.JWTUserDetailsService;
import ar.com.ada.api.cursos.services.UsuarioService;;

/**
 * AuthController
 */
@RestController
public class AuthController {

    @Autowired
    UsuarioService usuarioService;

    /*
     * @Autowired private AuthenticationManager authenticationManager;
     */
    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    @Autowired
    private JWTUserDetailsService userDetailsService;

    // Auth : authentication ->
    @PostMapping("/api/auth/register")
    public ResponseEntity<RegistrationResponse> postRegisterUser(@Valid @RequestBody RegistrationRequest req,
            BindingResult results) {
        if (results.hasErrors()) {
            RegistrationResponse r = new RegistrationResponse();
            r.isOk = false;
            r.message = "Errores en la registracion";
            results.getFieldErrors().stream().forEach(e -> {
                r.errors.add(new ErrorResponseItem(e.getField(), e.getDefaultMessage()));
            });
            return ResponseEntity.badRequest().body(r);
        }

        Usuario usuario = usuarioService.crearUsuario(req.userType, req.fullName, req.country, req.identificationType,
                req.identification, req.birthDate, req.email, req.password);
        return ResponseEntity
                .ok(new RegistrationResponse(true, "Te registraste con exitoooo!!!!!!!", usuario.getUsuarioId()));

    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody LoginRequest authenticationRequest,
            BindingResult results) throws Exception {
        if (results.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorResponse.FromBindingResults(results));
        }
        // no es recomendable usar Exception xq son lentas
        Usuario usuarioLogueado = usuarioService.login(authenticationRequest.username, authenticationRequest.password);

        UserDetails userDetails = usuarioService.getUserAsUserDetail(usuarioLogueado);
        Map<String, Object> claims = usuarioService.getUserClaims(usuarioLogueado);

        // Genero los roles con los Claims(los propositos)
        // Nuestros claims tienen info del tipo de usuario
        // y de la entidad que representa
        // Esta info va a viajar con el token, o sea, cualquiera puede
        // ver esos ids de que user pertenecen si logran interceptar el token
        // Por eso es que en cada request debemos validar el token(firma)
        String token = jwtTokenUtil.generateToken(userDetails, claims);

        return ResponseEntity.ok(new LoginResponse(usuarioLogueado.getUsuarioId(), authenticationRequest.username,
                token, usuarioLogueado.getEmail().toLowerCase(), usuarioLogueado.getTipoUsuarioId(),
                usuarioLogueado.obtenerEntityId()));

    }

}