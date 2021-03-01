package org.jesuitasrioja.proyectoFinalEval.controllers.responsable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResponsableNoEncontradoException extends RuntimeException {

	public ResponsableNoEncontradoException(String identificadorProducto) {
		super("teacher with " + identificadorProducto + " could not be retrieved");
	}

}
