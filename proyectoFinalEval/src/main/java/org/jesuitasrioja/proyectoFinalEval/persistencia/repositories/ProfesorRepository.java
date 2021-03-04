package org.jesuitasrioja.proyectoFinalEval.persistencia.repositories;

import org.jesuitasrioja.proyectoFinalEval.modelo.profesor.Profesor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, String> {

	@Query(value = "select a from Profesor a where a.nombre = :name", nativeQuery = true)
	public Page<Profesor> findByName(@Param("name") String name, Pageable pageable);

	public Page<Profesor> findByNombre(String nombre, Pageable pageable);
}
