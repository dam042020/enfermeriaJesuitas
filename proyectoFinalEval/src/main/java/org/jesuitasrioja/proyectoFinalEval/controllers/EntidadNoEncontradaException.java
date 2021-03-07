package org.jesuitasrioja.proyectoFinalEval.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EntidadNoEncontradaException extends RuntimeException {

	public EntidadNoEncontradaException(String identificador) {
		super("entity with " + identificador + " could not be retrieved");
	}

}
