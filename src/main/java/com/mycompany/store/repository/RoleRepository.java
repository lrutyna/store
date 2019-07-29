package com.mycompany.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.store.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Optional<Role> findOneByName(String name);
}
