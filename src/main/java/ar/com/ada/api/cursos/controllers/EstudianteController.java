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

    @Autowired
    CursoService cursoService;

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

    // @PostMapping("/api/estuadintes/{id}/inscripciones")
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
    // metodo para admin, obtener todos los estudiantes y todos los estudiantes que
    // no tienen cursos
    @GetMapping("/api/estudiantes")
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

    /*
     * - Estudiante -> Perspectiva estudiante: Ver cursos disponibles! (son todos
     * los cursos donde no este inscripto) - Estudiante -> ver mis cursos(solo
     * pueden verlo los estudiantes)
     * 
     * - /api/estudiantes/{id}/cursos?disponibles=true&categoria=1 o -
     * /api/estudiantes/{id}/cursos - /api/estudiantes/{id}/cursos/disponibles -> en
     * este caso es un metodo separado
     */
    @GetMapping("/api/estudiantes/{id}/cursos")
    public ResponseEntity<List<Curso>> listaCursos(@PathVariable Integer id,
            @RequestParam(value = "disponibles", required = false) boolean disponibles) throws Exception {
        List<Curso> listaCursos = new ArrayList<>();
        Estudiante estudiante = estudianteService.buscarPorId(id);
        if (disponibles) {
            // listaCursos = algo que nos devuelva la llista de cursos disponibles.
            listaCursos = cursoService.listaCursosDisponibles(estudiante);
        } else {
            listaCursos = estudiante.getCursosQueAsiste();
        }
        return ResponseEntity.ok(listaCursos);
    }
}
