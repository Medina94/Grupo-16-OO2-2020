package com.unla.Grupo16OO22020.services.implementation;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo16OO22020.converters.LocalidadConverter;
import com.unla.Grupo16OO22020.entities.Localidad;
import com.unla.Grupo16OO22020.models.LocalidadModel;
import com.unla.Grupo16OO22020.repositories.ILocalidadRepository;
import com.unla.Grupo16OO22020.services.ILocalidadService;


@Service("localidadService")
public class LocalidadService implements ILocalidadService {

	@Autowired
	@Qualifier("localidadRepository")
	private ILocalidadRepository localidadRepository;
	
	@Autowired
	@Qualifier("localidadConverter")
	private LocalidadConverter localidadConverter;
	
	@Override
	public List<Localidad> getAll() {
		return localidadRepository.findAll();
	}

	@Override
	public LocalidadModel insertOrUpdate(LocalidadModel LocalidadModel) {
		Localidad Localidad = localidadRepository.save(localidadConverter.modelToEntity(LocalidadModel));
		return localidadConverter.entityToModel(Localidad);
	}

	@Override
	public boolean remove(int id) {
		try {
			localidadRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public LocalidadModel findById(int id) {
		return localidadConverter.entityToModel(localidadRepository.findById(id));
	}
}
