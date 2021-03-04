package org.jesuitasrioja.proyectoFinalEval.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProfesorNoEncontradoException extends RuntimeException {

	public ProfesorNoEncontradoException(String identificadorProducto) {
		super("teacher with " + identificadorProducto + " could not be retrieved");
	}

}