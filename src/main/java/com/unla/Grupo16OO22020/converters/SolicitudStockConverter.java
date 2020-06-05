package com.unla.Grupo16OO22020.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.unla.Grupo16OO22020.entities.SolicitudStock;
import com.unla.Grupo16OO22020.models.SolicitudStockModel;

@Component("solicitudStockConverter")
public class SolicitudStockConverter {
	@Autowired
	@Qualifier("localConverter")
	private LocalConverter localConverter;
	
	public SolicitudStockModel entityToModel(SolicitudStock entity) {
		return new SolicitudStockModel(entity.getColaborador(), entity.getSolicitador(), entity.getEstado(), entity.getPedido(), localConverter.entityToModel(entity.getLocal()));
	}
	public SolicitudStock ModelToEntity(SolicitudStock model) {
		return new SolicitudStock(model.getSolicitador(), model.getEstado(), model.getPedido(), model.getLocal());
	}
}
