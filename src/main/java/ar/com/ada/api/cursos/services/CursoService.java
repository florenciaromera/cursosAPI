package ar.com.ada.api.cursos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.cursos.entities.Curso;
import ar.com.ada.api.cursos.entities.Docente;
import ar.com.ada.api.cursos.entities.Estudiante;
import ar.com.ada.api.cursos.repos.CursoRepository;

@Service
public class CursoService {
    // es un atributo de la clase CursoService
    @Autowired
    CursoRepository cursoRepository;
    @Autowired
    CategoriaService categoriaService;
    @Autowired
    DocenteService docenteService;

    // 21/07 21:39 Hernán dijo que algo vamos a tener que cambiar en el resultado
    public boolean crearCurso(Curso curso) {
        if (cursoRepository.existsByNombre(curso.getNombre()))
            return false;
        cursoRepository.save(curso);
        return true;
    }

    public Curso crearCurso(String nombre, Integer categoriaId, Integer duracionHoras, String descripcion) {
        Curso curso = new Curso();
        curso.setNombre(nombre);
        curso.agregarCategoria(categoriaService.buscarPorId(categoriaId));
        curso.setDuracionHoras(duracionHoras);
        curso.setDescripcion(descripcion);
        // llamo al metodo creado en la linea 19
        boolean cursoCreado = crearCurso(curso);
        if (cursoCreado)
            return curso;
        else
            return null;

        // if (cursoRepository.existsByNombre(curso.getNombre()))
        // return null;
        // cursoRepository.save(curso);
        // return curso;

    }

    public List<Curso> listaCursos() {
        return cursoRepository.findAll();
    }

    public List<Curso> listaCursosSinDocentes() {
        return cursoRepository.listaCursosSinDocentes();

    }

    public List<Curso> listaCursosDisponibles(Estudiante estudiante) {
        List<Curso> listaCursosDisponibles = new ArrayList<>();
        for (Curso curso : listaCursos()) {
            boolean anotado = curso.getEstudiantes().stream()
                    .anyMatch(e -> e.getEstudianteId().equals(estudiante.getEstudianteId()));
            if (!anotado) {
                listaCursosDisponibles.add(curso);
            }
        }

        return listaCursosDisponibles;
    }

    // public List<Curso> listaCursosDisponibles(Estudiante estudiante) {
    // return
    // cursoRepository.listaCursoDisponibleByEstudianteId(estudiante.getEstudianteId().intValue());
    // }

    public Curso buscarPorId(Integer id) throws Exception {
        Optional<Curso> opCurso = cursoRepository.findById(id);
        if (!opCurso.isPresent()) {
            throw new Exception("El curso con id " + id + " no existe");
        }
        return opCurso.get();
    }

    public void altaBajaDocente(Integer cursoId, Integer docenteId, String accion) throws Exception {
        Curso curso = buscarPorId(cursoId);
        boolean estaEnLaLista = curso.getDocentes().stream().anyMatch(d -> d.getDocenteId().equals(docenteId));
        if (!estaEnLaLista && accion.equalsIgnoreCase("Alta")) {
            altaDocente(curso, docenteId);
        } else if (estaEnLaLista && accion.equalsIgnoreCase("Baja")) {
            bajaDocente(curso, docenteId);
        } else {
            String mensaje = estaEnLaLista ? "El docente ya existe en el curso" : "El docente no existe en el curso";
            throw new Exception(mensaje);
        }

    }

    public void altaDocente(Curso curso, Integer docenteId) throws Exception {
        curso.asignarDocente(docenteService.buscarPorId(docenteId));
        cursoRepository.save(curso);
    }

    public void bajaDocente(Curso curso, Integer docenteId) {
        Docente docente = docenteService.buscarPorId(docenteId);
        curso.bajarDocente(docente);
        cursoRepository.save(curso);
    }
}