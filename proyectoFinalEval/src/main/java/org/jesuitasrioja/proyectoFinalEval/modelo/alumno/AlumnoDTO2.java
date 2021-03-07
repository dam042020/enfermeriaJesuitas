package org.jesuitasrioja.proyectoFinalEval.modelo.alumno;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * Alumno pagina alumnos
 * @author Sergio Ezquerro Ochagavia
 *
 */
public class AlumnoDTO2 implements Serializable {
	private String nombre;
	private String apellidos;
	private String nivel;
	private String grupo;
	private String telefono;
	private String correo;
}
