package org.jesuitasrioja.proyectoFinalEval.controllers.responsable;

import java.util.Optional;
import java.util.function.Function;

import org.jesuitasrioja.proyectoFinalEval.modelo.responsable.Responsable;
import org.jesuitasrioja.proyectoFinalEval.modelo.responsable.ResponsableDTO;
import org.jesuitasrioja.proyectoFinalEval.modelo.responsable.ResponsableDTO2;
import org.jesuitasrioja.proyectoFinalEval.modelo.responsable.ResponsableDTOConverter;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponsableController {

	private static final int pageSize = 20;

	@Autowired
	private ResponsableService ps;

	@Autowired
	private ResponsableDTOConverter responsableDTOConverter;

	// Uso ResponsableDTO2 -- Ofrece: nombre
	@GetMapping("/responsables")
	public ResponseEntity<?> allProducts(@RequestParam(name = "nombre") String nombre,
			@PageableDefault(size = pageSize, page = 0) Pageable pageable) {

		Page<Responsable> paginaResponsable = ps.encontrarPorNombre(nombre, pageable);

		// transformar elementos de la pagina a DTO
		Page<ResponsableDTO2> paginaDTO2 = paginaResponsable.map(new Function<Responsable, ResponsableDTO2>() {
			@Override
			public ResponsableDTO2 apply(Responsable t) {
				return responsableDTOConverter.convertResponsableToResponsableDTO2(t);
			}
		});

		return ResponseEntity.status(HttpStatus.OK).body(paginaDTO2);
	}

	// OJO: aqui uso el ResponsableDTO
	@GetMapping("/responsable/{id}")
	public ResponseEntity<?> getResponsable(@PathVariable String id) {
		Optional<Responsable> responsableOptional = ps.findById(id);
		if (responsableOptional.isPresent()) {
			Responsable responsable = responsableOptional.get();
			ResponsableDTO responsableDTO = responsableDTOConverter.convertResponsableToResponsableDTO(responsable);
			return ResponseEntity.status(HttpStatus.OK).body(responsableDTO);
		} else {
			throw new ResponsableNoEncontradoException(id);
		}
	}

	@GetMapping("/responsable")
	public Responsable getResponsable2(@RequestParam String id) {
		return ps.findById(id).get();
	}

	@PostMapping("/responsable")
	public String postResponsable(@RequestBody Responsable nuevoResponsable) {
		return ps.save(nuevoResponsable).toString();
	}

	@PutMapping("/responsable")
	public String putResponsable(@RequestBody Responsable editadoResponsable) {
		return ps.edit(editadoResponsable).toString();
	}

	@DeleteMapping("/responsable/{id}")
	public String deleteResponsable(@PathVariable String id) {
		ps.deleteById(id);
		return "OK";
	}

}
