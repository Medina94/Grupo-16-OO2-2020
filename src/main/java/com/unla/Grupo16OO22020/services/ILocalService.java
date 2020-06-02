package com.unla.Grupo16OO22020.services;

import java.util.List;

import com.unla.Grupo16OO22020.entities.Local;
import com.unla.Grupo16OO22020.models.LocalModel;
import com.unla.Grupo16OO22020.models.ProductoModel;


public interface ILocalService {

	public List<Local> getAll();
	
	public LocalModel findById(int id);
	
	public LocalModel insertOrUpdate(LocalModel localModel);
	
	public boolean remove(int id);
	
	public List<LocalModel> getLocalesCercanos(LocalModel localModel);
	
	public List<Local> localesConProducto(int idProducto);
	
	public List<LocalModel> getLocalesConStock(ProductoModel producto, int cantidadSolicitada, int cantidadLocales);
}
