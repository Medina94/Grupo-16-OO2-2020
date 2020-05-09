package com.unla.Grupo16OO22020.services;
import java.util.List;

import com.unla.Grupo16OO22020.entities.Localidad;
import com.unla.Grupo16OO22020.models.LocalidadModel;


public interface ILocalidadService {

	public List<Localidad> getAll();
	
	public LocalidadModel findById(int id);
	
	public LocalidadModel insertOrUpdate(LocalidadModel localidadModel);
	
	public boolean remove(int id);
	
}

