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

	@Query(value = "select a.Profesor from Alumno a where a.nombre = :name", nativeQuery = true)
	public Page<Profesor> findByAlumnName(@Param("name") String name, Pageable pageable);
	
	@Query(value = "select a.Profesor from Alumno a where a.identificador = :id", nativeQuery = true)
	public Page<Profesor> findByAlumnId(@Param("id") String id, Pageable pageable);
}
