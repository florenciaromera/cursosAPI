package ar.com.ada.api.cursos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ar.com.ada.api.cursos.entities.Contenido;
import ar.com.ada.api.cursos.models.request.ContenidoRequest;
import ar.com.ada.api.cursos.models.response.ContenidoResponse;
import ar.com.ada.api.cursos.models.response.GenericResponse;
import ar.com.ada.api.cursos.services.ContenidoService;

@Controller
public class ContenidoController {
    @Autowired
    ContenidoService contenidoService;

    @PostMapping("/api/contenidos")
    public ResponseEntity<GenericResponse> crearContenido(@RequestBody ContenidoRequest contenidoReq) {

        Contenido contenidoCreado = contenidoService.crearContenido(contenidoReq.descripcion,
                contenidoReq.descripcionLarga, contenidoReq.payload, contenidoReq.payloadSimple,
                contenidoReq.tipoContenidoId);

        if (contenidoCreado == null)
            return ResponseEntity.badRequest().build();

        GenericResponse gR = new GenericResponse();
        gR.isOk = true;
        gR.message = "Contenido creado con éxito";
        gR.id = contenidoCreado.getContenidoId();
        return ResponseEntity.ok(gR);
    }

    @GetMapping("/api/contenidos")
    public ResponseEntity<List<Contenido>> listaContenidos() {
        List<Contenido> listaContenidos = contenidoService.listarTodos();
        return ResponseEntity.ok(listaContenidos);
    }

    @GetMapping("/api/contenidos/{id}")
    public ResponseEntity<ContenidoResponse> contenidoPorId(@PathVariable Integer id) {
        Contenido cE = contenidoService.buscarPorId(id);
        ContenidoResponse cR = new ContenidoResponse();
        cR.contenidoId = cE.getContenidoId();
        cR.descripcion = cE.getDescripcion();
        cR.descripcionLarga = cE.getDescripcionLarga();
        cR.payload = cE.getPayload();
        cR.payloadSimple = cE.getPayloadSimple();
        cR.clase = cE.getClase();
        return ResponseEntity.ok(cR);
    }
}