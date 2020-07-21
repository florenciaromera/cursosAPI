package ar.com.ada.api.cursos.controllersTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.ada.api.cursos.controllers.CategoriaController;
import ar.com.ada.api.cursos.entities.Categoria;
import ar.com.ada.api.cursos.services.CategoriaService;

@SpringBootTest
public class CategoriaControllerTest {
    @Autowired
    Categoria categoria;

    @InjectMocks
    CategoriaController categoriaController;

    @Mock
    CategoriaService categoriaService;

    @Test
    void crearCategoriaSinCursoTest() {
        Categoria categoria = categoriaService.crearCategoria("Matematicas", "vemos algebra");
        assertTrue(categoria.getCategoriaId().compareTo(0) == 1);

        Integer nuevaCategoriaId = categoria.getCategoriaId();

        Categoria categoriaDesdeDB = categoriaService.buscarPorId(nuevaCategoriaId);
        assertTrue(categoriaDesdeDB != null);
        assertEquals("Matematicas", categoriaDesdeDB.getNombre());
        assertEquals("vemos algebra", categoriaDesdeDB.getDescripcion());
    }
}