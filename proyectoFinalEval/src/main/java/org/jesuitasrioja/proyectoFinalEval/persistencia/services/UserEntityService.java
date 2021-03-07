package org.jesuitasrioja.proyectoFinalEval.persistencia.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.jesuitasrioja.proyectoFinalEval.modelo.user.UserEntity;
import org.jesuitasrioja.proyectoFinalEval.modelo.user.UserRole;
import org.jesuitasrioja.proyectoFinalEval.persistencia.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserEntityService extends BaseService<UserEntity, String, UserEntityRepository> {
	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;

	public Optional<UserEntity> findByUserName(String username) {
		return this.repositorio.findByUsername(username);
	}

	// metodo que permite registrar un nuevo usuario en el sistema
	public UserEntity nuevoUsuario(UserEntity userEntity) {
		userEntity.setId(String.valueOf(Math.abs(new Random().nextInt())));
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

		// en caso de no haber asignado un rol en la peticion, aniadimos uno por defecto
		Set<UserRole> defaultRoles = new HashSet<UserRole>();
		defaultRoles.add(UserRole.USER);

		if (userEntity.getRoles() == null) {
			userEntity.setRoles(defaultRoles);
		} else if (userEntity.getRoles().size() == 0) {
			userEntity.setRoles(defaultRoles);
		}
		return this.repositorio.save(userEntity);
	}
}
