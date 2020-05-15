package com.unla.Grupo16OO22020.services;
import java.util.List;

import com.unla.Grupo16OO22020.entities.Lote;
import com.unla.Grupo16OO22020.models.LoteModel;


public interface ILoteService {

	public List<Lote> getAll();
	
	public LoteModel findById(int id);
	
	public LoteModel insertOrUpdate(LoteModel loteModel);
	
	public boolean remove(int id);
	
}

