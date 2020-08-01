package ar.com.ada.api.cursos.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.cursos.entities.*;
import ar.com.ada.api.cursos.models.request.InscripcionRequest;
import ar.com.ada.api.cursos.models.response.GenericResponse;
import ar.com.ada.api.cursos.services.*;

@RestController
public class EstudianteController {

    @Autowired
    EstudianteService estudianteService;

    @PostMapping("/api/estudiantes")
    public ResponseEntity<GenericResponse> crearEstudiante(@RequestBody Estudiante estudiante) {
        GenericResponse r = new GenericResponse();
        if (estudianteService.estudianteExiste(estudiante)) {
            r.isOk = false;
            r.message = "Este estudiante ya existe";

            return ResponseEntity.badRequest().body(r);
        }

        estudianteService.crearEstudiante(estudiante);

        r.isOk = true;
        r.message = "Estudiante creada con exito";
        r.id = estudiante.getEstudianteId();
        return ResponseEntity.ok(r);

    }

    // @PostMapping("/api/esduaintes/{id}/inscripciones")
    // ResponseEntity<GenericResponse> inscribir(@PathVariable Integer estudianteId,
    // @RequestBody InscripcionRequest iR) {
    // GenericResponse gR = new GenericResponse();
    // Inscripcion inscripcionCreada = estudianteService.inscribir(estudianteId,
    // iR.cursoId);
    // if (inscripcionCreada == null) {
    // gR.isOk = false;
    // gR.message = "La inscripcion no pudo ser realizada";
    // }
    // gR.isOk = true;
    // gR.message = "La inscripcion fue realizada con exito";
    // gR.id = inscripcionCreada.getInscripcionId();
    // return ResponseEntity.ok(gR);
    // }

    @GetMapping("/api/estudiantes/{id}")
    ResponseEntity<Estudiante> buscarPorIdEstudiante(@PathVariable Integer id) throws Exception {
        Estudiante estudiante = estudianteService.buscarPorId(id);
        if (estudiante == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(estudiante);
    }

    // con filtro sin cursos: /api/estudiantes?sinCursos=true
    @GetMapping("/api/estudiantes/cursos")
    ResponseEntity<List<Estudiante>> listarEstudiantes(
            @RequestParam(value = "sinCursos", required = false) boolean sinCursos) {
        List<Estudiante> listaEstudiantes = new ArrayList<>();
        if (sinCursos) {
            listaEstudiantes = estudianteService.listaEstudianTesSinCurso();
        } else {
            listaEstudiantes = estudianteService.listaEstudiantes();
        }

        return ResponseEntity.ok(listaEstudiantes);
    }
}
