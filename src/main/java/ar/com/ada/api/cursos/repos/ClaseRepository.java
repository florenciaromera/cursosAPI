package ar.com.ada.api.cursos.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.ada.api.cursos.entities.Clase;

public interface ClaseRepository extends JpaRepository<Clase, Integer> {

}