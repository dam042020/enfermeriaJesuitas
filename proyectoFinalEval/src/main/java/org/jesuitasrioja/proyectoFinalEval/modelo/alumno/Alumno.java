package org.jesuitasrioja.proyectoFinalEval.modelo.alumno;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.jesuitasrioja.proyectoFinalEval.modelo.profesor.Profesor;
import org.jesuitasrioja.proyectoFinalEval.modelo.responsable.Responsable;

import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Alumno")
public class Alumno {
	@Id
	@Include
	@ApiModelProperty(value = "identificador del alumno", dataType = "String", position = 1, example = "1")
	private String identificador;

	@ApiModelProperty(value = "dni del alumno", dataType = "String", position = 2, example = "12345678A")
	private String dni;

	@ApiModelProperty(value = "nombre del alumno", dataType = "String", position = 3, example = "Aitor")
	private String nombre;

	@ApiModelProperty(value = "apellidos del alumno", dataType = "String", position = 4, example = "Ezquerro Navajas")
	private String apellidos;

	@ApiModelProperty(value = "fecha de nacimiento del alumno", dataType = "DateTime", position = 5, example = "01/02/2000")
	private Date fechaNacimiento;

	@ApiModelProperty(value = "telefono del alumno", dataType = "String", position = 6, example = "123456789")
	private String telefono;

	@ApiModelProperty(value = "direccion del alumno", dataType = "String", position = 7, example = "Logroño, Calle: calleFalsa123, N: 3, Piso:E")
	private String direccion;

	@ApiModelProperty(value = "correo del alumno", dataType = "String", position = 8, example = "aaa@gmail.com")
	private String correo;

	@ApiModelProperty(value = "nivel del alumno", dataType = "String", position = 9, example = "FP")
	private String nivel;

	@ApiModelProperty(value = "grupo del alumno", dataType = "String", position = 10, example = "32E")
	private String grupo;

	@ApiModelProperty(value = "descripcion medica del alumno", dataType = "String", position = 11, example = "Grupo de sangre A; Asmático")
	private String descripcionMedica;
	
	@ApiModelProperty(value = "tutor del alumno", dataType = "Profesor", position = 12)
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "tutor")
	private Profesor tutor;

	@ApiModelProperty(value = "responsable del alumno", dataType = "Responsable", position = 13)
	@OneToMany(mappedBy = "identificador", cascade = CascadeType.ALL)
	private List<Responsable> responsables;
}
