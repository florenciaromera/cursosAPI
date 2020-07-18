package ar.com.ada.api.cursos.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.cursos.entities.*;
import ar.com.ada.api.cursos.models.response.GenericResponse;
import ar.com.ada.api.cursos.services.*;

@RestController
public class CategoriaController {

    // Declarar un service

    @Autowired
    CategoriaService categoriaService;

    // Post: que recibios algo, que nos permite instanciar una Categoria y ponerle
    // datos.
    @PostMapping("/categorias")
    ResponseEntity<GenericResponse> crearCategoria(@RequestBody Categoria categoria) {

        categoriaService.crearCategoria(categoria);

        GenericResponse r = new GenericResponse();
        r.isOk = true;
        r.message = "Categoria Creada con exito";
        r.id = categoria.getCategoriaId();

        // Aca vamos a usar Ok
        // Cuando se crea, generalmetnte para los puristas, se usa el
        // CreatedAtAction(201)
        return ResponseEntity.ok(r);

    }
}