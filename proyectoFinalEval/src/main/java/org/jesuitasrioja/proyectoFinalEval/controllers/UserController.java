package org.jesuitasrioja.proyectoFinalEval.controllers;

import java.util.List;

import org.jesuitasrioja.proyectoFinalEval.modelo.user.UserEntity;
import org.jesuitasrioja.proyectoFinalEval.persistencia.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserController {

	@Autowired
	private UserEntityService ues;

	// registrará un nuevo usuario
	@PostMapping("/user")
	public ResponseEntity<UserEntity> nuevoUsuario(@RequestBody UserEntity newUser) {
		try {
			// en caso de querer registrar un usuario cuyo nombre ya esté en el sistema
			// devolveremos un código de error 409 - Conflict.
			if (!ues.findByUserName(newUser.getUsername()).isPresent()) {
				return ResponseEntity.status(HttpStatus.CREATED).body(ues.nuevoUsuario(newUser));
			} else {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
		} catch (DataIntegrityViolationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	// mostrará todos los usuarios registrados en el sistema
	@GetMapping("/users")
	public ResponseEntity<List<UserEntity>> getUsuarios() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(ues.findAll());
		} catch (DataIntegrityViolationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
