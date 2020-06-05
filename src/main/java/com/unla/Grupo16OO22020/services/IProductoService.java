package com.unla.Grupo16OO22020.services;
import java.util.List;

import com.unla.Grupo16OO22020.entities.Producto;
import com.unla.Grupo16OO22020.models.ProductoModel;

public interface IProductoService {

	public List<Producto> getAll();
	
	public abstract List<Producto> traerTodoProductoDeLocal(int localId);
	
	public ProductoModel findById(int id);
	
	public ProductoModel insertOrUpdate(ProductoModel loteModel);
	
	public boolean remove(int id);
	
	public ProductoModel findByCodigoAndLocal(String codigo, int localId);

	public ProductoModel codigoProducto(ProductoModel productoModel);
	
}

