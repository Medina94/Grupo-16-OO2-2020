package com.unla.Grupo16OO22020.repositories;

import com.unla.Grupo16OO22020.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface IRoleRepository extends JpaRepository<UserRole, Long>{
	
	public abstract UserRole findByrole(String rol);
	
}
