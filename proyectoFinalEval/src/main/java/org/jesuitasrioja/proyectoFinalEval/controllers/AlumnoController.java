package org.jesuitasrioja.proyectoFinalEval.controllers;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.jesuitasrioja.proyectoFinalEval.modelo.alumno.Alumno;
import org.jesuitasrioja.proyectoFinalEval.modelo.alumno.AlumnoDTO;
import org.jesuitasrioja.proyectoFinalEval.modelo.alumno.AlumnoDTO2;
import org.jesuitasrioja.proyectoFinalEval.modelo.alumno.AlumnoDTOConverter;
import org.jesuitasrioja.proyectoFinalEval.modelo.profesor.Profesor;
import org.jesuitasrioja.proyectoFinalEval.modelo.responsable.Responsable;
import org.jesuitasrioja.proyectoFinalEval.persistencia.services.AlumnoService;
import org.jesuitasrioja.proyectoFinalEval.persistencia.services.ProfesorService;
import org.jesuitasrioja.proyectoFinalEval.persistencia.services.ResponsableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class AlumnoController {

	private static final int pageSize = 20;

	@Autowired
	private AlumnoService as;

	@Autowired
	private ProfesorService ps;

	@Autowired
	private ResponsableService rs;

	@Autowired
	private AlumnoDTOConverter alumnoDTOConverter;

	// GET alumnos
	// AlumnoDTO2
	@ApiOperation(value = "Obtener todos los alumnos en paginas", notes = "Este metodo manda todos los alumnos, paginados de 20 en 20")
	@GetMapping("/alumnos")
	public ResponseEntity<?> allAlumnos(@PageableDefault(size = pageSize, page = 0) Pageable pageable) {
		Page<Alumno> paginaAlumno = as.findAll(pageable);

		// transformar elementos de la pagina a DTO
		Page<AlumnoDTO2> paginaDTO2 = paginaAlumno.map(new Function<Alumno, AlumnoDTO2>() {
			@Override
			public AlumnoDTO2 apply(Alumno t) {
				return alumnoDTOConverter.convertAlumnoToAlumnoDTO2(t);
			}
		});

		return ResponseEntity.status(HttpStatus.OK).body(paginaDTO2);
	}

	// GET alumno/{idAlumno}
	// AlumnoDTO
	@ApiOperation(value = "Obtener el alumno con la id proporcionada", notes = "Este metodo devuelve el alumno con la id proporcionada")
	@GetMapping("/alumno/{id}")
	public ResponseEntity<?> getAlumno(@PathVariable String id) {
		Optional<Alumno> alumnoOptional = as.findById(id);
		if (alumnoOptional.isPresent()) {
			Alumno alumno = alumnoOptional.get();
			AlumnoDTO alumnoDTO = alumnoDTOConverter.convertAlumnoToAlumnoDTO(alumno);
			return ResponseEntity.status(HttpStatus.OK).body(alumnoDTO);
		} else {
			throw new EntidadNoEncontradaException(id);
		}
	}

	// POST alumno
	@ApiOperation(value = "Aniadir alumno", notes = "Este metodo aniade el alumno proporcionado")
	@PostMapping("/alumno")
	public String postAlumno(@RequestBody Alumno nuevoAlumno) {
		return as.save(nuevoAlumno).toString();
	}

	// PUT alumno/{idAlumno}
	@ApiOperation(value = "Modificar alumno", notes = "Este metodo permite modificar un Alumno.")
	@PutMapping("/alumno")
	public String putAlumno(@RequestBody Alumno editadoAlumno) {
		return as.edit(editadoAlumno).toString();
	}

	// DELETE alumno/{idAlumno}
	@ApiOperation(value = "Borrar alumno", notes = "Metodo para borrar un Alumno especifico por identificador")
	@DeleteMapping("/alumno/{idAlumno}")
	public String deleteAlumno(@PathVariable String idAlumno) {
		as.deleteById(idAlumno);
		return "OK";
	}

	// PUT alumno/{idAlumno}/profesor/{idProfesor}
	@ApiOperation(value = "Asociar profesor a Alumno", notes = "Metodo para asociar un Profesor a un Alumno.")
	@PutMapping("/alumno/{idAlumno}/profesor/{idProfesor}")
	public ResponseEntity<String> asociarProfesorAlumno(@PathVariable String idAlumno,
			@PathVariable String idProfesor) {
		Optional<Alumno> alumnoOptional = as.findById(idAlumno);
		Optional<Profesor> profesorOptional = ps.findById(idProfesor);

		if (alumnoOptional.isPresent() && profesorOptional.isPresent()) {
			Alumno alumnoEditado = alumnoOptional.get();
			alumnoEditado.setTutor(profesorOptional.get());
			return ResponseEntity.status(HttpStatus.OK).body(as.edit(alumnoEditado).toString());
		} else {
			throw new EntidadNoEncontradaException("Alumno: " + idAlumno + ", Profesor: " + idProfesor);
		}
	}

	// POST alumno/{idAlumno}/responsable
	@ApiOperation(value = "Aniadir Responsable a Alumno", notes = "Metodo para aniadir un Responsable a un Alumno.")
	@PostMapping("/alumno/{idAlumno}/responsable")
	public ResponseEntity<String> aniadirResponsableAlumno(@PathVariable String idAlumno,
			@RequestBody Responsable nuevoResponsable) {

		Optional<Alumno> alumnoOptional = as.findById(idAlumno);
		if (alumnoOptional.isPresent()) {
			Alumno alumnoModificado = alumnoOptional.get();
			List<Responsable> lista = alumnoModificado.getResponsables();
			lista.add(nuevoResponsable);
			alumnoModificado.setResponsables(lista);
			return ResponseEntity.status(HttpStatus.OK).body(as.save(alumnoModificado).toString());
		} else {
			throw new EntidadNoEncontradaException("Alumn: " + idAlumno);
		}
	}
	
	// DELETE alumno/{idAlumno}/responsable/{idResponsable}
	@ApiOperation(value = "Eliminar Responsable de Alumno",
			 notes = "Metodo para desvincular un Responsable de un Alumno")
	@DeleteMapping("/alumno/{idAlumno}/responsable/{idResponsable}")
	public ResponseEntity<String> borrarResponsableAlumno(@PathVariable String idAlumno, @PathVariable String idResponsable) {
		
		Optional<Alumno> alumnoOptional = as.findById(idAlumno);
		if(alumnoOptional.isPresent()) {
			Alumno alumnoModificado = alumnoOptional.get();
			List<Responsable> lista = alumnoModificado.getResponsables();
			Optional<Responsable> responsableBorrar = rs.findById(idResponsable);
			lista.remove(responsableBorrar.get());
			alumnoModificado.setResponsables(lista);
			return ResponseEntity.status(HttpStatus.OK).body(as.save(alumnoModificado).toString());
		} else {
			throw new EntidadNoEncontradaException("Alumn: " + idAlumno);
		}
	}
}
