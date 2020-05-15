package com.unla.Grupo16OO22020.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.unla.Grupo16OO22020.entities.Lote;
import com.unla.Grupo16OO22020.models.LoteModel;

@Component("loteConverter")
public class LoteConverter {

	@Autowired
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;
	
	public LoteModel entityToModel(Lote lote) {
		return new LoteModel(lote.getId(), lote.getCantidad(), lote.getFechaIngreso(), productoConverter.entityToModel(lote.getProducto()));
	}

	public Lote modelToEntity(LoteModel loteModel) {
		return new Lote(loteModel.getId(), loteModel.getCantidad(), loteModel.getFechaIngreso(), productoConverter.modelToEntity(loteModel.getProductoModel()));
	}
}

