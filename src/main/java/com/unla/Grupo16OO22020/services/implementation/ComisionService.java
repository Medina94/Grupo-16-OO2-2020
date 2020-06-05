package com.unla.Grupo16OO22020.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo16OO22020.converters.ComisionConverter;
import com.unla.Grupo16OO22020.entities.Comision;
import com.unla.Grupo16OO22020.models.ComisionModel;
import com.unla.Grupo16OO22020.repositories.IComisionRepository;
import com.unla.Grupo16OO22020.services.IComisionService;

@Service("comisionService")
public class ComisionService implements IComisionService{

	@Autowired
	@Qualifier("comisionRepository")
	private IComisionRepository comisionRepository;
	
	@Autowired
	@Qualifier("comisionConverter")
	private ComisionConverter comisionConverter;
	
	@Override
	public ComisionModel insertOrUpdate(ComisionModel comisionModel) {
		Comision comision = comisionRepository.save(comisionConverter.modelToEntity(comisionModel));
		return comisionConverter.entityToModel(comision);
	}
	
	@Override
	public ComisionModel findById(int id) {
		return comisionConverter.entityToModel(comisionRepository.findById(id));
	}
	
	@Override
	public List<Comision> getAll(){
		return comisionRepository.findAll();
	}
	
	
}
