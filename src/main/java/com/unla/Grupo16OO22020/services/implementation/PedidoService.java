package com.unla.Grupo16OO22020.services.implementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo16OO22020.converters.PedidoConverter;
import com.unla.Grupo16OO22020.entities.Lote;
import com.unla.Grupo16OO22020.entities.Pedido;
import com.unla.Grupo16OO22020.models.PedidoModel;
import com.unla.Grupo16OO22020.models.ProductoModel;
import com.unla.Grupo16OO22020.repositories.ILoteRepository;
import com.unla.Grupo16OO22020.repositories.IPedidoRepository;
import com.unla.Grupo16OO22020.services.IPedidoService;


@Service("pedidoService")
public class PedidoService implements IPedidoService {

	@Autowired
	@Qualifier("pedidoRepository")
	private IPedidoRepository pedidoRepository;	
	@Autowired
	@Qualifier("loteRepository")
	private ILoteRepository loteRepository;	
	@Autowired
	@Qualifier("pedidoConverter")
	private PedidoConverter pedidoConverter;		
	@Autowired
	@Qualifier("personaService")
	private PersonaService personaService;
	@Autowired
	@Qualifier("productoService")
	private ProductoService productoService;
	@Autowired
	@Qualifier("loteService")
	private LoteService loteService;
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

	@Override
	public boolean consultarStock(int idProducto, int cantidadSolicitada) {
		List<Lote> lotes = loteService.findByProducto(idProducto);
		int stock = lotes.stream().filter(x->x.getCantidad()>0).mapToInt(x->x.getCantidad()).sum();
		if(stock >= cantidadSolicitada) {
			return true;
		}		
		return false;
	}

	@Override
	public void actualizarStock(PedidoModel pedidoModel) {
		List<Lote> lotes = loteService.findByProducto(pedidoModel.getProductoModel().getId());
		int cantidadActualizada = pedidoModel.getCantidadSolicitada();
		for(Lote l: lotes) {
			 cantidadActualizada =  cantidadActualizada - l.getCantidad();
			if (cantidadActualizada >= 0) {
			    Lote lote = loteRepository.findById(l.getId());
			    lote.setCantidad(0);
				loteRepository.save(lote);
			}else if(cantidadActualizada < 0){
				Lote lote = loteRepository.findById(l.getId());
				lote.setCantidad(Math.abs(cantidadActualizada));
				loteRepository.save(lote);
			}
		}
	}

	@Override
	public void rechazarPedido(int pedidoId) {
		Pedido pedido = pedidoRepository.findById(pedidoId);		
		Lote lote = new Lote(0, pedido.getCantidadSolicitada(),LocalDate.now(),pedido.getProducto());
	    loteRepository.save(lote);
	}

	@Override
	public PedidoModel calcularTotal(int pedidoId) {
	PedidoModel pedido = pedidoConverter.entityToModel(pedidoRepository.findById(pedidoId));
	pedido.setTotal(pedido.getCantidadSolicitada() * pedido.getProductoModel().getPrecioUnitario());
	return pedido;
	}

}
