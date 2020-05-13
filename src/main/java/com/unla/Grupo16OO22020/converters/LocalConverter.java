package com.unla.Grupo16OO22020.converters;

import org.springframework.stereotype.Component;

import com.unla.Grupo16OO22020.entities.Local;
import com.unla.Grupo16OO22020.models.LocalModel;


@Component("localConverter")
public class LocalConverter {

	public LocalModel entityToModel(Local Local) {
		return new LocalModel(Local.getId(), Local.getTelefono(), Local.getDireccion(), Local.getLatitud(), Local.getLongitud());
	}

	public Local modelToEntity(LocalModel LocalModel) {
		return new Local(LocalModel.getId(), LocalModel.getTelefono(), LocalModel.getDireccion(), LocalModel.getLatitud(), LocalModel.getLongitud());
	}
}
