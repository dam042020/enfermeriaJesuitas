package org.jesuitasrioja.proyectoFinalEval.modelo.profesor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Profesor")
public class Profesor {
	@Id
	@ApiModelProperty(value = "identificador del profesor", dataType = "String", position = 1, example = "1")
	private String identificador;

	@ApiModelProperty(value = "nombre del profesor", dataType = "String", position = 2, example = "Aitor")
	private String nombre;

	@ApiModelProperty(value = "telefono del profesor", dataType = "String", position = 3, example = "123456789")
	private String telefono;
}
