package org.jesuitasrioja.proyectoFinalEval.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AlumnoNoEncontradoException extends RuntimeException {

	public AlumnoNoEncontradoException(String identificadorProducto) {
		super("alumn with " + identificadorProducto + " could not be retrieved");
	}

}
