package ar.com.ada.api.cursos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.cursos.entities.Clase;
import ar.com.ada.api.cursos.models.response.GenericResponse;
import ar.com.ada.api.cursos.services.ClaseService;

@RestController
public class ClaseController {
    @Autowired
    ClaseService claseService;

    // @PostMapping("/api/clases")
    // public ResponseEntity<GenericResponse> crearClase(@RequestBody Clase clase) {
    // Clase claseCreada = claseService.crearClase(clase.getNumero(),
    // clase.getTitulo());
    // if (claseCreada == null)
    // return ResponseEntity.badRequest().build();
    // GenericResponse gR = new GenericResponse();
    // gR.isOk = true;
    // gR.message = "Clase creada con exito";
    // gR.id = claseCreada.getClaseId();
    // return ResponseEntity.ok(gR);
    // }

    // @PutMapping(("/api/categorias/{id}"))
    // ResponseEntity<GenericResponse> actualizarCategoriaPorId(@PathVariable
    // Integer id,
    // @RequestBody CategoriaRequest cMR) {
    // Categoria categoria = categoriaService.buscarPorId(id);
    // if (categoria == null) {
    // return ResponseEntity.notFound().build();
    // }

    // categoria.setNombre(cMR.nombre);
    // categoria.setDescripcion(cMR.descripcion);
    // Categoria categoriaActualizada =
    // categoriaService.actualizarCategoria(categoria);

    // GenericResponse r = new GenericResponse();
    // r.isOk = true;
    // r.message = "Categoria actualizada con éxito";
    // r.id = categoriaActualizada.getCategoriaId();

    // return ResponseEntity.ok(r);
    // }

    // @GetMapping("/api/categorias/{id}")
    // ResponseEntity<CategoriaResponse> buscarPorIdCategoria(@PathVariable Integer
    // id) {
    // Categoria categoria = categoriaService.buscarPorId(id);

    // CategoriaResponse cGR = new CategoriaResponse();
    // cGR.nombre = categoria.getNombre();
    // cGR.descripcion = categoria.getDescripcion();
    // cGR.categoriaId = categoria.getCategoriaId();
    // cGR.cursosLista = categoria.getCursos();

    // return ResponseEntity.ok(cGR);
    // }

    // @GetMapping("/api/categorias")
    // ResponseEntity<List<CategoriaResponse>> listarCategorias() {
    // List<Categoria> listaCategorias = categoriaService.listarTodas();
    // List<CategoriaResponse> listaCategoriasResponse = new
    // ArrayList<CategoriaResponse>();
    // for (Categoria c : listaCategorias) {
    // CategoriaResponse cR = new CategoriaResponse();
    // cR.nombre = c.getNombre();
    // cR.descripcion = c.getDescripcion();
    // cR.categoriaId = c.getCategoriaId();
    // cR.cursosLista = c.getCursos();
    // listaCategoriasResponse.add(cR);
    // }
    // return ResponseEntity.ok(listaCategoriasResponse);
    // }

}