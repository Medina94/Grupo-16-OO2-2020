package com.unla.Grupo16OO22020.converters;

import org.springframework.stereotype.Component;

import com.unla.Grupo16OO22020.entities.Comision;
import com.unla.Grupo16OO22020.models.ComisionModel;


@Component("comisionConverter")
public class ComisionConverter {

	public ComisionModel entityToModel(Comision comision) {
		return new ComisionModel(comision.getId(),comision.getPlusVenta(), comision.getPlusCeder(), comision.getPlusPedido(), comision.getFecha());
	}

	public Comision modelToEntity(ComisionModel ComisionModel) {
		return new Comision(ComisionModel.getId(),ComisionModel.getPlusVenta(), ComisionModel.getPlusCeder(), ComisionModel.getPlusPedido(), ComisionModel.getFecha());
	}
}
