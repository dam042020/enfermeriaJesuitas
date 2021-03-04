package org.jesuitasrioja.proyectoFinalEval.persistencia.repositories;

import org.jesuitasrioja.proyectoFinalEval.modelo.responsable.Responsable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsableRepository extends JpaRepository<Responsable, String> {

	@Query(value = "select a from Responsable a where a.nombre = :name", nativeQuery = true)
	public Page<Responsable> findByName(@Param("name") String name, Pageable pageable);

	@Query(value = "select a.Responsable from Alumno a where a.nombre = :name", nativeQuery = true)
	public Page<Responsable> findByAlumnName(@Param("name") String name, Pageable pageable);
	
	@Query(value = "select a.Responsable from Alumno a where a.identificador = :id", nativeQuery = true)
	public Page<Responsable> findByAlumnId(@Param("id") String id, Pageable pageable);
}
