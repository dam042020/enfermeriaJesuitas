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

	@ApiModelProperty(value = "dni del profesor", dataType = "String", position = 2, example = "12345678A")
	private String dni;

	@ApiModelProperty(value = "nombre del profesor", dataType = "String", position = 3, example = "Aitor")
	private String nombre;

	@ApiModelProperty(value = "fecha de nacimiento del profesor", dataType = "String", position = 4, example = "01/02/2000")
	private String fechaNacimiento;

	@ApiModelProperty(value = "telefono del profesor", dataType = "String", position = 5, example = "123456789")
	private String telefono;

	@ApiModelProperty(value = "direccion del profesor", dataType = "String", position = 6, example = "Logroño, Calle: calleFalsa123, N: 3, Piso:E")
	private String direccion;

}
