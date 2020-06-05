package com.unla.Grupo16OO22020.services;

import java.util.List;

import com.unla.Grupo16OO22020.entities.Comision;
import com.unla.Grupo16OO22020.models.ComisionModel;

public interface IComisionService {

	public List<Comision> getAll();
	
	public ComisionModel findById(int id);
	
	public ComisionModel insertOrUpdate(ComisionModel comisionModel);
	
}
