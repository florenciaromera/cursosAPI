package ar.com.ada.api.cursos.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ar.com.ada.api.cursos.models.request.CursoAsigDocRequest;
import ar.com.ada.api.cursos.models.request.CursoRequest;
import ar.com.ada.api.cursos.models.response.GenericResponse;
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
      // listaCursos = algo que nos devuelva la llista sin docentes.
      listaCursos = cursoService.listaCursosSinDocentes();
    } else {
      listaCursos = cursoService.listaCursos();
    }

    return ResponseEntity.ok(listaCursos);

  }

  // - Asignar Docente a un curso.
  // /api/cursos/docentes/25 : este representaria al id del docente
  // /api/cursos/25/docentes: este prepresentaria al id del curso.
  // podemos usar este metodo para asignar y dar de baja docente, usando los
  // mismos parámetros
  // se cambia el nombre altaBajaDocente (lo mismo se puede hacer en estudiante)
  @PostMapping("/api/cursos/{cursoId}/docentes")
  public ResponseEntity<GenericResponse> asignarDocente(@PathVariable Integer cursoId,
      @RequestBody CursoAsigDocRequest cADR) throws Exception {

    GenericResponse gR = new GenericResponse();
    try {

      cursoService.altaBajaDocente(cursoId, cADR.docenteId, cADR.accion);
      gR.isOk = true;
      gR.message = "El docente ha sido dado de " + cADR.accion + " del curso";
      return ResponseEntity.ok(gR);
    } catch (Exception exc) {
      gR.message = "No se pudo realizar la accion " + cADR.accion;
      return ResponseEntity.badRequest().body(gR);
      // throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo
      // realizar la accion " + cADR.accion);
    }
  }
}
