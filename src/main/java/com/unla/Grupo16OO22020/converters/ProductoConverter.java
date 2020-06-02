package com.unla.Grupo16OO22020.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.unla.Grupo16OO22020.entities.Producto;
import com.unla.Grupo16OO22020.models.ProductoModel;

@Component("productoConverter")
public class ProductoConverter {
	
	@Autowired
	@Qualifier("localConverter")
	private LocalConverter localConverter;
	
	public ProductoModel entityToModel(Producto producto) {
		return new ProductoModel(producto.getId(), producto.getDescripcion(), producto.getPrecioUnitario(), localConverter.entityToModel(producto.getLocal()), producto.getFechaAlta(), producto.isEliminado(), producto.getCodigo());
	}

	public Producto modelToEntity(ProductoModel productoModel) {
		return new Producto(productoModel.getId(), productoModel.getDescripcion(), productoModel.getPrecioUnitario(), productoModel.getFechaAlta() ,localConverter.modelToEntity(productoModel.getLocalModel()), productoModel.isEliminado(), productoModel.getCodigo());
	}
}


