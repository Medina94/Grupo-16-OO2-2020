package com.unla.Grupo16OO22020.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo16OO22020.converters.PedidoConverter;
import com.unla.Grupo16OO22020.entities.Pedido;
import com.unla.Grupo16OO22020.models.PedidoModel;
import com.unla.Grupo16OO22020.repositories.IPedidoRepository;
import com.unla.Grupo16OO22020.services.IPedidoService;


@Service("pedidoService")
public class PedidoService implements IPedidoService {

	@Autowired
	@Qualifier("pedidoRepository")
	private IPedidoRepository pedidoRepository;	
	@Autowired
	@Qualifier("pedidoConverter")
	private PedidoConverter pedidoConverter;		
	@Autowired
	@Qualifier("personaService")
	private PersonaService personaService;
	@Autowired
	@Qualifier("productoService")
	private ProductoService productoService;
	@Override
	
	public List<Pedido> getAll() {
		return pedidoRepository.findAll();
	}

	@Override
	public PedidoModel insertOrUpdate(PedidoModel pedidoModel) {
		pedidoModel.setProductoModel(productoService.findById(pedidoModel.getProductoModel().getId()));
		pedidoModel.setClienteModel(personaService.clienteFindById(pedidoModel.getClienteModel().getId()));
		pedidoModel.setSolicitadorModel(personaService.empleadoFindById(pedidoModel.getSolicitadorModel().getId()));
		Pedido pedido = pedidoRepository.save(pedidoConverter.modelToEntity(pedidoModel));
		return pedidoConverter.entityToModel(pedido);
	}

	@Override
	public boolean remove(int id) {
		try {
			pedidoRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public PedidoModel findById(int id) {
		return pedidoConverter.entityToModel(pedidoRepository.findById(id));
	}

}
