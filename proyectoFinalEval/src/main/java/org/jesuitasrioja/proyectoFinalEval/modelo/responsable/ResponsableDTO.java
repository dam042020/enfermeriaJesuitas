package org.jesuitasrioja.proyectoFinalEval.modelo.responsable;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsableDTO implements Serializable {
	private String identificador;
	private String nombre;
	private String parentesco;
	private String telefono;
}
