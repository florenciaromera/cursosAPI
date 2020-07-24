package ar.com.ada.api.cursos.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.cursos.models.request.CursoRequest;
import ar.com.ada.api.cursos.models.response.GenericResponse;
import ar.com.ada.api.cursos.services.CursoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import ar.com.ada.api.cursos.entities.*;

@RestController
public class CursoController {

    @Autowired
    CursoService cursoService;

    @PostMapping("/api/cursos")
    public ResponseEntity<GenericResponse> crearCurso(@RequestBody CursoRequest cursoReq) {

        Curso cursoCreado = cursoService.crearCurso(cursoReq.nombre, cursoReq.categoriaId, cursoReq.duracionHoras,
                cursoReq.descripcion);

        if (cursoCreado == null)
            return ResponseEntity.badRequest().build();

        GenericResponse gR = new GenericResponse();
        gR.isOk = true;
        gR.message = "Curso creado con éxito";
        gR.id = cursoCreado.getCursoId();
        return ResponseEntity.ok(gR);

        // GenericResponse gR = new GenericResponse();
        // return ResponseEntity.ok(gR);
    }

    @GetMapping("/api/cursos")
    public ResponseEntity<List<Curso>> listaCursos() {
        List<Curso> listaCursos = cursoService.listaCursos();
        return ResponseEntity.ok(listaCursos);
    }

}