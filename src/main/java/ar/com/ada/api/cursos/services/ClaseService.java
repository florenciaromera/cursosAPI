package ar.com.ada.api.cursos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.cursos.entities.Clase;
import ar.com.ada.api.cursos.repos.ClaseRepository;

@Service
public class ClaseService {

    @Autowired
    ClaseRepository claseRepo;

    public void crearClase(Clase clase) {
        claseRepo.save(clase);
    }

    public Clase crearClase(Integer numeroClase, String titulo) {
        Clase clase = new Clase();
        clase.setTitulo(titulo);
        clase.setNumero(numeroClase);
        claseRepo.save(clase);
        return clase;
    }
}