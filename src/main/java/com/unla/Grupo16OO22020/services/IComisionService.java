package com.unla.Grupo16OO22020.services;

import java.util.List;

import com.unla.Grupo16OO22020.entities.Comision;
import com.unla.Grupo16OO22020.models.ComisionModel;
import com.unla.Grupo16OO22020.models.EmpleadoModel;

public interface IComisionService {

	
	public ComisionModel findById(int id);
	
	public ComisionModel insertOrUpdate(ComisionModel comisionModel);
	
//	public void calculoDeComision(int año,int mes,EmpleadoModel empleadoModel);
	
	public List<Comision> getAll();
}
