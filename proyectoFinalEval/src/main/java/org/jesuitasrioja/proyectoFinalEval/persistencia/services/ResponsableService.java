package org.jesuitasrioja.proyectoFinalEval.persistencia.services;

import java.util.Optional;

import org.jesuitasrioja.proyectoFinalEval.modelo.responsable.Responsable;
import org.jesuitasrioja.proyectoFinalEval.persistencia.repositories.ResponsableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ResponsableService extends BaseService<Responsable, String, ResponsableRepository> {
	// modo manual que se utiliza cuando los métodos del repository son suficientes.
	public void cambiarNombre(String identificador, String nombre) {
		Optional<Responsable> pOptional = this.repositorio.findById(identificador);
		if (pOptional.isPresent()) {
			Responsable p = pOptional.get();
			p.setNombre(nombre);
			this.repositorio.save(p);
		}
	}

	public Page<Responsable> encontrarPorNombre(String nombre, Pageable p) {
		return this.repositorio.findByName(nombre, p);
	}
	
	public Optional<Responsable> encontrarPorId(String id) {
		return this.repositorio.findById(id);
	}

}
