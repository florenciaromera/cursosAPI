package ar.com.ada.api.cursos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.cursos.entities.*;
import ar.com.ada.api.cursos.models.request.CategoriaModifRequest;
import ar.com.ada.api.cursos.models.response.CategoriaResponse;
import ar.com.ada.api.cursos.models.response.GenericResponse;
import ar.com.ada.api.cursos.security.controllersSecurity.ControllersSecurity;
import ar.com.ada.api.cursos.services.*;

@RestController
public class CategoriaController {

    // Declarar un service

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    ControllersSecurity controllersSecurity;

    @PostMapping("/api/categorias")
    @PreAuthorize("@controllersSecurity.isStaff(principal)")
    public ResponseEntity<GenericResponse> crearCategoria(@RequestBody Categoria categoria) {
        categoriaService.crearCategoria(categoria);
        return ResponseEntity.ok(new GenericResponse(true, "Categoria Creada con exito", categoria.getCategoriaId()));

    }

    // Metodo Verificacion 2: haciendo lo mismo que antes, pero usando
    // Spring Expression LANGUAGE(magic)
    // Aca el principal es el User, este principal no es el mismo principal del
    // metodo anterior
    // pero apunta a uno parecido(el de arriba es el principal authentication)
    // https://docs.spring.io/spring-security/site/docs/3.0.x/reference/el-access.html
    @PutMapping(("/api/categorias/{id}"))
    @PreAuthorize("@controllersSecurity.isStaff(principal)") // En este caso quiero que sea STAFF(3)
    ResponseEntity<GenericResponse> actualizarCategoriaPorId(@PathVariable Integer id,
            @RequestBody CategoriaModifRequest cMR) {
        Categoria categoria = categoriaService.buscarPorId(id);
        if (categoria == null) {
            return ResponseEntity.notFound().build();
        }

        categoria.setNombre(cMR.nombre);
        categoria.setDescripcion(cMR.descripcion);
        Categoria categoriaActualizada = categoriaService.actualizarCategoria(categoria);

        GenericResponse r = new GenericResponse();
        r.isOk = true;
        r.message = "Categoria actualizada con éxito";
        r.id = categoriaActualizada.getCategoriaId();

        return ResponseEntity.ok(r);
    }

    // Autorizacion Modo 3
    // Metodo Verificacion 3: haciendo lo mismo que antes, pero leyendo
    // desde el el authority. O sea , cuando creamos el User para el UserDetails(no
    // el usuario)
    // Le seteamos una autoridad sobre el tipo de usuario
    // Esto lo que hace es preguntar si tiene esa autoridad seteada.
    // Dentro de este, tenemos 2 formas de llenar el Authority
    // Llenandolo desde la Base de datos, o desde el JWT
    // Desde la DB nos da mas seguridad pero cada vez que se ejecute es ir a buscar
    // a la DB
    // Desde el JWT, si bien exponemos el entityId/usertype, nos permite evitarnos
    // ir a la
    // db.
    // Este CLAIM lo podemos hacer con cualquier propiedad que querramos mandar
    // al JWT
    @GetMapping("/api/categorias/{id}")
    @PreAuthorize("@controllersSecurity.isStaff(principal)")
    ResponseEntity<CategoriaResponse> buscarPorIdCategoria(@PathVariable Integer id) {
        Categoria categoria = categoriaService.buscarPorId(id);

        CategoriaResponse cGR = new CategoriaResponse();
        cGR.nombre = categoria.getNombre();
        cGR.descripcion = categoria.getDescripcion();

        return ResponseEntity.ok(cGR);
    }

    @GetMapping("/api/categorias")
    @PreAuthorize("@controllersSecurity.isStaff(principal)")
    ResponseEntity<List<Categoria>> listarCategorias() {
        List<Categoria> listaCategorias = categoriaService.listarTodas();

        /*
         * List<CategoriaResponse> listaCategoriasResponse = new
         * ArrayList<CategoriaResponse>(); for (Categoria c : listaCategorias) {
         * CategoriaResponse cR = new CategoriaResponse(); cR.nombre = c.getNombre();
         * cR.descripcion = c.getDescripcion(); listaCategoriasResponse.add(cR); }
         * return ResponseEntity.ok(listaCategoriasResponse);
         */
        return ResponseEntity.ok(listaCategorias);
    }
}