package com.unla.Grupo16OO22020.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo16OO22020.converters.LoteConverter;
import com.unla.Grupo16OO22020.entities.Lote;
import com.unla.Grupo16OO22020.models.LoteModel;
import com.unla.Grupo16OO22020.models.ProductoModel;
import com.unla.Grupo16OO22020.repositories.ILoteRepository;
import com.unla.Grupo16OO22020.services.ILoteService;


@Service("loteService")
public class LoteService implements ILoteService {

	@Autowired
	@Qualifier("loteRepository")
	private ILoteRepository loteRepository;
	
	@Autowired
	@Qualifier("loteConverter")
	private LoteConverter loteConverter;
	@Autowired
	@Qualifier("productoService")
	private ProductoService productoService;
	@Autowired
	@Qualifier("localService")
	private LocalService localService;
	@Override
	
	public List<Lote> getAll() {
		return loteRepository.findAll();
	}

	@Override
	public LoteModel insertOrUpdate(LoteModel loteModel) {
		loteModel.setProductoModel(productoService.findById(loteModel.getProductoModel().getId()));
		Lote lote = loteRepository.save(loteConverter.modelToEntity(loteModel));
		return loteConverter.entityToModel(lote);
	}

	@Override
	public boolean remove(int id) {
		try {
			loteRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public LoteModel findById(int id) {
		return loteConverter.entityToModel(loteRepository.findById(id));
	}

	@Override
	public List<Lote> findByProducto(String codigo, int idLocal) {
		return	loteRepository.findByProducto(codigo, idLocal);
		
	}

}
