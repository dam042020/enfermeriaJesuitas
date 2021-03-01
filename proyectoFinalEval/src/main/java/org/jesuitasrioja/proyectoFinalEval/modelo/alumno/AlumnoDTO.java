package org.jesuitasrioja.proyectoFinalEval.modelo.alumno;

import java.io.Serializable;
import java.util.Set;

import org.jesuitasrioja.proyectoFinalEval.modelo.profesor.Profesor;
import org.jesuitasrioja.proyectoFinalEval.modelo.responsable.Responsable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoDTO implements Serializable {
	private String identificador;
	private String nombre;
	private String dni;
	private String fechaNacimiento;
	private String telefono;
	private String direccion;
	private Profesor tutor;
	private Set<Responsable> responsables;
}
