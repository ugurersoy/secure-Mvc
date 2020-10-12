package com.secure.doc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secure.doc.model.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, Long>  {

	
	Optional<UserEntity> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
