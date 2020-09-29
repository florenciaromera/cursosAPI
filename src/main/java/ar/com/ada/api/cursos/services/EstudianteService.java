package ar.com.ada.api.cursos.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.cursos.entities.*;
import ar.com.ada.api.cursos.entities.Inscripcion.EstadoInscripcionEnum;
import ar.com.ada.api.cursos.entities.Pais.PaisEnum;
import ar.com.ada.api.cursos.entities.Pais.TipoDocuEnum;
import ar.com.ada.api.cursos.repos.*;
import ar.com.ada.api.cursos.sistema.comm.EmailService;

@Service
public class EstudianteService {

    @Autowired
    EstudianteRepository estudianteRepository;

    @Autowired
    CursoService cursoService;

    @Autowired
    EmailService emailService;

    public boolean crearEstudiante(Estudiante estudiante) {
        if (estudianteRepository.existsEstudiante(estudiante.getPaisId(), estudiante.getTipoDocumentoId().getValue(),
                estudiante.getDocumento()))
            return false;
        estudianteRepository.save(estudiante);
        return true;
    }

    public Estudiante crearEstudiante(String nombre, PaisEnum paisEnum, TipoDocuEnum TipoDocuEnum, String documento,
            Date fechaNacimiento) throws Exception {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(nombre);
        estudiante.setPaisId(paisEnum.getValue());
        estudiante.setTipoDocumentoId(TipoDocuEnum);
        estudiante.setDocumento(documento);
        estudiante.setFechaNacimiento(fechaNacimiento);
        boolean estudianteCreado = crearEstudiante(estudiante);
        if (!estudianteCreado)
            throw new Exception("El estudiante ya existe");
        else
            return estudiante;
    }

    public Estudiante buscarPorId(Integer id) throws Exception {
        Optional<Estudiante> opEstudiante = estudianteRepository.findById(id);

        if (!opEstudiante.isPresent()) {
            throw new Exception("El curso estudiante con id " + id + " no existe");
        }
        return opEstudiante.get();
    }

    public List<Estudiante> listaEstudiantes() {
        return estudianteRepository.findAll();
    }

    public List<Estudiante> listaEstudianTesSinCurso() {
        return estudianteRepository.buscarEstudiantesSinCurso();
    }

    public boolean estudianteExiste(Estudiante estudiante) {

        if (estudianteRepository.existsEstudiante(estudiante.getPaisId(), estudiante.getTipoDocumentoId().getValue(),
                estudiante.getDocumento()))
            return true;
        else
            return false;

    }

    public Inscripcion inscribir(Integer estudianteId, Integer cursoId) throws Exception {
        Estudiante estudiante = buscarPorId(estudianteId);
        Curso curso = cursoService.buscarPorId(cursoId);
        Inscripcion inscripcion = new Inscripcion();

        inscripcion.setFecha(new Date());
        inscripcion.setEstadoInscripcion(EstadoInscripcionEnum.ACTIVO);

        // inscripcion.setCurso(curso);
        inscripcion.setUsuario(estudiante.getUsuario());

        curso.agregarInscripcion(inscripcion);
        curso.asignarEstudiante(estudiante);

        estudianteRepository.save(estudiante);
        return inscripcion;
    }
}