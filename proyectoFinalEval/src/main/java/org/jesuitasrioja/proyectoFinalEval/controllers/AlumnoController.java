package org.jesuitasrioja.proyectoFinalEval.controllers;

import java.util.Optional;
import java.util.function.Function;

import org.jesuitasrioja.proyectoFinalEval.modelo.alumno.Alumno;
import org.jesuitasrioja.proyectoFinalEval.modelo.alumno.AlumnoDTO;
import org.jesuitasrioja.proyectoFinalEval.modelo.alumno.AlumnoDTO2;
import org.jesuitasrioja.proyectoFinalEval.modelo.alumno.AlumnoDTOConverter;
import org.jesuitasrioja.proyectoFinalEval.persistencia.services.AlumnoService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlumnoController {

	private static final int pageSize = 20;

	@Autowired
	private AlumnoService ps;

	@Autowired
	private AlumnoDTOConverter alumnoDTOConverter;

	// Uso ProfesorDTO2 -- Ofrece: nombre
	@GetMapping("/alumnos")
	public ResponseEntity<?> allProducts(@RequestParam(name = "nombre") String nombre,
			@PageableDefault(size = pageSize, page = 0) Pageable pageable) {

		Page<Alumno> paginaAlumno = ps.encontrarPorNombre(nombre, pageable);

		// transformar elementos de la pagina a DTO
		Page<AlumnoDTO2> paginaDTO2 = paginaAlumno.map(new Function<Alumno, AlumnoDTO2>() {
			@Override
			public AlumnoDTO2 apply(Alumno t) {
				return alumnoDTOConverter.convertAlumnoToAlumnoDTO2(t);
			}
		});

		return ResponseEntity.status(HttpStatus.OK).body(paginaDTO2);
	}

	// OJO: aqui uso el ProfesorDTO
	@GetMapping("/alumno/{id}")
	public ResponseEntity<?> getAlumno(@PathVariable String id) {
		Optional<Alumno> alumnoOptional = ps.findById(id);
		if (alumnoOptional.isPresent()) {
			Alumno alumno = alumnoOptional.get();
			AlumnoDTO alumnoDTO = alumnoDTOConverter.convertAlumnoToAlumnoDTO(alumno);
			return ResponseEntity.status(HttpStatus.OK).body(alumnoDTO);
		} else {
			throw new AlumnoNoEncontradoException(id);
		}
	}

	@GetMapping("/alumno")
	public Alumno getAlumno2(@RequestParam String id) {
		return ps.findById(id).get();
	}

	@PostMapping("/alumno")
	public String postAlumno(@RequestBody Alumno nuevoAlumno) {
		return ps.save(nuevoAlumno).toString();
	}

	@PutMapping("/alumno")
	public String putAlumno(@RequestBody Alumno editadoAlumno) {
		return ps.edit(editadoAlumno).toString();
	}

	@DeleteMapping("/alumno/{id}")
	public String deleteAlumno(@PathVariable String id) {
		ps.deleteById(id);
		return "OK";
	}

}
