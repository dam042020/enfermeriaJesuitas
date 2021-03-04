package org.jesuitasrioja.proyectoFinalEval.modelo.profesor;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorDTO implements Serializable {
	private String identificador;
	private String nombre;
	private String telefono;
}
