package com.unla.Grupo16OO22020.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.Grupo16OO22020.entities.Comision;
import com.unla.Grupo16OO22020.models.ComisionModel;

@Repository("comisionRepository")
public interface IComisionRepository  extends JpaRepository<Comision, Serializable>{

	public abstract Comision findById(int id);
	
	@Query("SELECT c FROM Comision c WHERE c.fecha<NOW()")
	public abstract ComisionModel traerMasReciente();
	
}
