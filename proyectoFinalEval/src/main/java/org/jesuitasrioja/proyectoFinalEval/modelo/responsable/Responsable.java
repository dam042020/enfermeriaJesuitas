package org.jesuitasrioja.proyectoFinalEval.modelo.responsable;

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
@Table(name = "Responsable")
public class Responsable {
	@Id
	@ApiModelProperty(value = "identificador del responsable", dataType = "String", position = 1, example = "1")
	private String identificador;

	@ApiModelProperty(value = "dni del responsable", dataType = "String", position = 2, example = "12345678A")
	private String parentesco;

	@ApiModelProperty(value = "nombre del responsable", dataType = "String", position = 3, example = "Aitor")
	private String nombre;

	@ApiModelProperty(value = "telefono del responsable", dataType = "String", position = 4, example = "123456789")
	private String telefono;

}
