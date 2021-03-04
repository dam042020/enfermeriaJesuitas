package org.jesuitasrioja.proyectoFinalEval.controllers.profesor;

import java.util.Optional;
import java.util.function.Function;

import org.jesuitasrioja.proyectoFinalEval.modelo.profesor.Profesor;
import org.jesuitasrioja.proyectoFinalEval.modelo.profesor.ProfesorDTO;
import org.jesuitasrioja.proyectoFinalEval.modelo.profesor.ProfesorDTO2;
import org.jesuitasrioja.proyectoFinalEval.modelo.profesor.ProfesorDTOConverter;
import org.jesuitasrioja.proyectoFinalEval.persistencia.services.ProfesorService;
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
public class ProfesorController {

	private static final int pageSize = 20;

	@Autowired
	private ProfesorService ps;

	@Autowired
	private ProfesorDTOConverter profesorDTOConverter;

	// Uso ResponsableDTO2 -- Ofrece: nombre
	@GetMapping("/profesors")
	public ResponseEntity<?> allProducts(@RequestParam(name = "nombre") String nombre,
			@PageableDefault(size = pageSize, page = 0) Pageable pageable) {

		Page<Profesor> paginaProfesor = ps.encontrarPorNombre(nombre, pageable);

		// transformar elementos de la pagina a DTO
		Page<ProfesorDTO2> paginaDTO2 = paginaProfesor.map(new Function<Profesor, ProfesorDTO2>() {
			@Override
			public ProfesorDTO2 apply(Profesor t) {
				return profesorDTOConverter.convertProfesorToProfesorDTO2(t);
			}
		});

		return ResponseEntity.status(HttpStatus.OK).body(paginaDTO2);
	}

	// OJO: aqui uso el ResponsableDTO
	@GetMapping("/profesor/{id}")
	public ResponseEntity<?> getProfesor(@PathVariable String id) {
		Optional<Profesor> profesorOptional = ps.findById(id);
		if (profesorOptional.isPresent()) {
			Profesor profesor = profesorOptional.get();
			ProfesorDTO profesorDTO = profesorDTOConverter.convertProfesorToProfesorDTO(profesor);
			return ResponseEntity.status(HttpStatus.OK).body(profesorDTO);
		} else {
			throw new ProfesorNoEncontradoException(id);
		}
	}

	@GetMapping("/profesor")
	public Profesor getProfesor2(@RequestParam String id) {
		return ps.findById(id).get();
	}

	@PostMapping("/profesor")
	public String postProfesor(@RequestBody Profesor nuevoProfesor) {
		return ps.save(nuevoProfesor).toString();
	}

	@PutMapping("/profesor")
	public String putProfesor(@RequestBody Profesor editadoProfesor) {
		return ps.edit(editadoProfesor).toString();
	}

	@DeleteMapping("/profesor/{id}")
	public String deleteProfesor(@PathVariable String id) {
		ps.deleteById(id);
		return "OK";
	}

}
