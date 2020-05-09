package com.unla.Grupo16OO22020.converters;

import org.springframework.stereotype.Component;

import com.unla.Grupo16OO22020.entities.Localidad;
import com.unla.Grupo16OO22020.models.LocalidadModel;

@Component("localidadConverter")
public class LocalidadConverter {

	public LocalidadModel entityToModel(Localidad Localidad) {
		return new LocalidadModel(Localidad.getId(),Localidad.getNombre(),Localidad.getLatitud(), Localidad.getLongitud());
	}

	public Localidad modelToEntity(LocalidadModel LocalidadModel) {
		return new Localidad(LocalidadModel.getId(), LocalidadModel.getNombre(), LocalidadModel.getLongitud(),LocalidadModel.getLatitud());
	}
}

