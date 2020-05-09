package com.unla.Grupo16OO22020.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.Grupo16OO22020.entities.Localidad;

@Repository("localidadRepository")
public interface ILocalidadRepository extends JpaRepository<Localidad, Serializable> {
	
	public abstract Localidad findById(int id);	
	
}
