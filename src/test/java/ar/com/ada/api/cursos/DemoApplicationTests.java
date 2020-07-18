package ar.com.ada.api.cursos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.ada.api.cursos.entities.Categoria;
import ar.com.ada.api.cursos.repos.CategoriaRepository;
import java.util.*;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	CategoriaRepository repoCategoria;

	@Test
	void crearCategoriaSinCursoTest() {
		tituloTestInicio("Categoria sin Curso");
		Categoria categoria = new Categoria();

		categoria.setNombre("Matematicas");
		categoria.setDescripcion("vemos algebra");

		repoCategoria.save(categoria);
		// si id era int hubiera sido assertTrue(categoria.getCategoriaId() > 0)
		// como el id es Integer se utiliza el compareTo que devuelve:
		// -1 si a < parametro
		// 0 si a == 1
		// 1 si a > parametro
		assertTrue(categoria.getCategoriaId().compareTo(0) == 1);

		Integer nuevaCategoriaId = categoria.getCategoriaId();

		// Busca la categoria que tiene el ID en la base de datos
		// particularmente el findById del repo: devuelve un Optional
		// Optional va a haber que checkear si el valor existe o no.
		Optional<Categoria> catDesdeDBResultado = repoCategoria.findById(nuevaCategoriaId);

		assertTrue(catDesdeDBResultado.isPresent());

		Categoria categoriaDesdeDB = catDesdeDBResultado.get();

		assertEquals("Matematicas", categoriaDesdeDB.getNombre());
		assertEquals("vemos algebra", categoriaDesdeDB.getDescripcion());

		tituloTestFin("Categoria sin Curso");

	}

	void tituloTestInicio(String titulo) {
		System.out.println("*************************************");
		System.out.println("*************************************");
		System.out.println("INICIO TEST: " + titulo);
		System.out.println("*************************************");
		System.out.println("*************************************");
	}

	void tituloTestFin(String titulo) {
		System.out.println("*************************************");
		System.out.println("*************************************");
		System.out.println("FIN TEST OK:  " + titulo);
		System.out.println("*************************************");
		System.out.println("*************************************");
	}

}
