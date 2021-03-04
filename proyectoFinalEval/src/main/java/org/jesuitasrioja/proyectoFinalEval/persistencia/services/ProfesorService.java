package org.jesuitasrioja.proyectoFinalEval.persistencia.services;

import java.util.Optional;

import org.jesuitasrioja.proyectoFinalEval.modelo.profesor.Profesor;
import org.jesuitasrioja.proyectoFinalEval.persistencia.repositories.ProfesorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProfesorService extends BaseService<Profesor, String, ProfesorRepository> {
	// modo manual que se utiliza cuando los métodos del repository son suficientes.
	public void cambiarNombre(String identificador, String nombre) {
		Optional<Profesor> pOptional = this.repositorio.findById(identificador);
		if (pOptional.isPresent()) {
			Profesor p = pOptional.get();
			p.setNombre(nombre);
			this.repositorio.save(p);
		}
	}

	public Page<Profesor> encontrarPorNombre(String nombre, Pageable p) {
		return this.repositorio.findByName(nombre, p);
	}
	
	public Optional<Profesor> encontrarPorId(String id) {
		return this.repositorio.findById(id);
	}

}
