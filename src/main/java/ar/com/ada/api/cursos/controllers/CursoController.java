package ar.com.ada.api.cursos.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.cursos.models.request.CursoAsigDocRequest;
import ar.com.ada.api.cursos.models.request.CursoRequest;
import ar.com.ada.api.cursos.models.response.GenericResponse;
import ar.com.ada.api.cursos.security.controllersSecurity.ControllersSecurity;
import ar.com.ada.api.cursos.services.CursoService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import ar.com.ada.api.cursos.entities.*;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CursoController {

  @Autowired
  CursoService cursoService;

  @Autowired
  ControllersSecurity controllersSecurity;

  @PostMapping("/api/cursos")
  @PreAuthorize("@controllersSecurity.isStaff(principal)")
  public ResponseEntity<GenericResponse> crearCurso(@RequestBody CursoRequest cursoReq) {

    Curso cursoCreado = cursoService.crearCurso(cursoReq.nombre, cursoReq.categoriaId, cursoReq.duracionHoras,
        cursoReq.descripcion);

    return cursoCreado != null
        ? ResponseEntity.ok(new GenericResponse(true, "Curso creado con éxito", cursoCreado.getCursoId()))
        : ResponseEntity.badRequest().build();
  }

  // sin filtro: /api/cursos
  // con filtro sin docentes: /api/cursos?sinDocentes=true
  // /api/cursos?docentes=null
  // sinDocentes es un queryParam que nos permite FILTRAR
  // front si envia ese parametro con el valor true, filtramos.
  @GetMapping("/api/cursos")
  public ResponseEntity<List<Curso>> listaCursos(
      @RequestParam(value = "sinDocentes", required = false) boolean sinDocentes) {
    List<Curso> listaCursos = new ArrayList<>();
    if (sinDocentes) {
      listaCursos = cursoService.listaCursosSinDocentes();
    } else {
      listaCursos = cursoService.listaCursos();
    }

    return ResponseEntity.ok(listaCursos);

  }
  
  @PostMapping("/api/cursos/{cursoId}/docentes")
  @PreAuthorize("@controllersSecurity.isStaff(principal)")
  public ResponseEntity<GenericResponse> asignarDocente(@PathVariable Integer cursoId,
      @RequestBody CursoAsigDocRequest cADR) throws Exception {
    try {
      cursoService.altaBajaDocente(cursoId, cADR.docenteId, cADR.accion);
      return ResponseEntity.ok(new GenericResponse(true, "El docente ha sido dado de " + cADR.accion + " del curso"));
    } catch (Exception exc) {
      return ResponseEntity.badRequest()
          .body(new GenericResponse(false, "No se pudo realizar la accion " + cADR.accion));
    }
  }
}
