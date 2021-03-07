package org.jesuitasrioja.proyectoFinalEval.modelo.alumno;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.jesuitasrioja.proyectoFinalEval.modelo.profesor.Profesor;
import org.jesuitasrioja.proyectoFinalEval.modelo.responsable.Responsable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * Alumno pagina alumno
 * @author Sergio Ezquerro Ochagavia
 *
 */
public class AlumnoDTO implements Serializable {
	private String nombre;
	private String apellidos;
	private Date fechaNacimiento;
	private String nivel;
	private String grupo;
	private String telefono;
	private String correo;
	private String descripcionMedica;
	private Profesor tutor;
	private List<Responsable> responsables;
}
