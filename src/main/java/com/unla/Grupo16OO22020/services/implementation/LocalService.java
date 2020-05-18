package com.unla.Grupo16OO22020.services.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo16OO22020.converters.LocalConverter;
import com.unla.Grupo16OO22020.entities.Local;
import com.unla.Grupo16OO22020.models.LocalModel;
import com.unla.Grupo16OO22020.repositories.ILocalRepository;
import com.unla.Grupo16OO22020.services.ILocalService;




@Service("localService")
public class LocalService implements ILocalService{

	@Autowired
	@Qualifier("localRepository")
	private ILocalRepository localRepository;
	
	@Autowired
	@Qualifier("localConverter")
	private LocalConverter localConverter;
	
	@Override
	public List<Local> getAll() {
		return localRepository.findAll();
	}

	@Override
	public LocalModel insertOrUpdate(LocalModel LocalModel) {
		Local Local = localRepository.save(localConverter.modelToEntity(LocalModel));
		return localConverter.entityToModel(Local);
	}

	@Override
	public boolean remove(int id) {
		try {
			localRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public LocalModel findById(int id) {
		return localConverter.entityToModel(localRepository.findById(id));
	}

	@Override
	public List<LocalModel> getLocalesCercanos(LocalModel localModel) {
		List<LocalModel> lista = new ArrayList<LocalModel>();
		for(Local local : getAll()) {
			LocalModel modelo = localConverter.entityToModel(local);
			if(modelo.getId() != localModel.getId()) {
				modelo.setDistanciaDelOrigen(distanciaCoord(localModel.getLatitud(), localModel.getLongitud(), modelo.getLatitud(), modelo.getLongitud()));	
				lista.add(modelo);
			}
		}
		Collections.sort(lista);
		return lista.subList(0, 2);
	}
	
	public static double distanciaCoord(double lat1, double lng1, double lat2, double lng2) {
		double radioTierra = 6371; //en kilómetros
		double dLat = Math.toRadians(lat2 - lat1);
		double dLng = Math.toRadians(lng2 - lng1);
		double sindLat = Math.sin(dLat / 2);
		double sindLng = Math.sin(dLng / 2);
		double va1 = Math.pow(sindLat, 2)
		+ Math.pow(sindLng, 2) * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
		double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
		return radioTierra * va2;
		}
}
