package org.jesuitasrioja.proyectoFinalEval.persistencia.repositories;

import java.util.Optional;

import org.jesuitasrioja.proyectoFinalEval.modelo.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, String> {
	Optional<UserEntity> findByUsername(String username);
}
