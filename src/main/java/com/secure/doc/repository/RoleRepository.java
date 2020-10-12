package com.secure.doc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secure.doc.enums.EnumRole;
import com.secure.doc.model.RoleEntity;


public interface RoleRepository extends JpaRepository<RoleEntity, Long>{

	
	Optional<RoleEntity> findByName(EnumRole name);
	
	
//	@Query(value="SELECT * FROM roles WHERE name = :name1 ;",nativeQuery = true)
//	Optional<RoleEntity> findRole(@Param("name1")String name1);
//	

	
	
	
}
