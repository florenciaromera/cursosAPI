package ar.com.ada.api.cursos;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.ada.api.cursos.entities.Categoria;
import ar.com.ada.api.cursos.repos.CategoriaRepository;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	CategoriaRepository repoCategoria;

	@Test
	void crearCategoriaSinCursoTest() {
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
	}

}
