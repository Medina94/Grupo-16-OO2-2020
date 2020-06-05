package com.unla.Grupo16OO22020.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.Grupo16OO22020.entities.Comision;

@Repository("comisionRepository")
public interface IComisionRepository  extends JpaRepository<Comision, Serializable>{

	public abstract Comision findById(int id);
	
}
