package ar.com.ada.api.cursos.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.cursos.entities.Contenido;

@Repository
public interface ContenidoRepository extends JpaRepository<Contenido, Integer> {

}