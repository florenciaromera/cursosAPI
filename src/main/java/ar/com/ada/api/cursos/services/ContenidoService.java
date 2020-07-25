package ar.com.ada.api.cursos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.cursos.entities.Contenido;
import ar.com.ada.api.cursos.entities.Contenido.TipoContenidoEnum;
import ar.com.ada.api.cursos.repos.ContenidoRepository;

@Service
public class ContenidoService {
    @Autowired
    ContenidoRepository contenidoRepo;

    public void crearContenido(Contenido contenido) {
        contenidoRepo.save(contenido);
    }

    public Contenido crearContenido(String descripcion, String descripcionLarga, String payload, String payloadSimple,
            TipoContenidoEnum tipoContenidoId) {
        Contenido contenido = new Contenido();
        contenido.setDescripcion(descripcion);
        contenido.setDescripcionLarga(descripcionLarga);
        contenido.setPayload(payload);
        contenido.setPayloadSimple(payloadSimple);
        contenido.setTipoContenidoId(tipoContenidoId);
        contenidoRepo.save(contenido);
        return contenido;
    }

    public Contenido buscarPorId(Integer id) {
        Optional<Contenido> opContenido = contenidoRepo.findById(id);
        if (opContenido.isPresent())
            return opContenido.get();
        else
            return null;

    }

    public List<Contenido> listarTodos() {
        return contenidoRepo.findAll();
    }

}