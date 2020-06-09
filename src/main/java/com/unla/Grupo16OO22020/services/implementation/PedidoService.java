package com.unla.Grupo16OO22020.services.implementation;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo16OO22020.converters.PedidoConverter;
import com.unla.Grupo16OO22020.converters.PersonaConverter;
import com.unla.Grupo16OO22020.entities.Empleado;
import com.unla.Grupo16OO22020.entities.Local;
import com.unla.Grupo16OO22020.entities.Lote;
import com.unla.Grupo16OO22020.entities.Pedido;
import com.unla.Grupo16OO22020.entities.Producto;
import com.unla.Grupo16OO22020.models.LocalModel;
import com.unla.Grupo16OO22020.models.PedidoModel;
import com.unla.Grupo16OO22020.models.ProductoModel;
import com.unla.Grupo16OO22020.models.RankingModel;
import com.unla.Grupo16OO22020.repositories.ILoteRepository;
import com.unla.Grupo16OO22020.repositories.IPedidoRepository;
import com.unla.Grupo16OO22020.repositories.IProductoRepository;
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
	@Qualifier("personaConverter")
	private PersonaConverter personaConverter;	
	@Autowired
	@Qualifier("personaService")
	private PersonaService personaService;
	@Autowired
	@Qualifier("productoService")
	private ProductoService productoService;
	@Autowired
	@Qualifier("loteService")
	private LoteService loteService;
	@Autowired
	@Qualifier("localService")
	private LocalService localService;
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Override	
	public List<Pedido> getAll() {
		return pedidoRepository.findAll();
	}

	@Override
	public PedidoModel insertOrUpdate(PedidoModel pedidoModel) {
		pedidoModel.setProductoModel(productoService.findById(pedidoModel.getProductoModel().getId()));
		pedidoModel.setClienteModel(personaService.clienteFindById(pedidoModel.getClienteModel().getId()));
		pedidoModel.setSolicitadorModel(personaConverter.EmpleadoEntitytoModel(userService.traerEmpleadoLogueado()));
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
		ProductoModel producto = productoService.findById(idProducto);
		List<Lote> lotes = loteService.traerTodoLoteDelLocalPorProducto(producto.getCodigo(), producto.getLocalModel().getId());
		int stock = lotes.stream().filter(x->x.getCantidad()>0).mapToInt(x->x.getCantidad()).sum();
		if(stock >= cantidadSolicitada) {
			return true;
		}		
		return false;
	}
	


	@Override
	public void actualizarStock(PedidoModel pedidoModel) {
		ProductoModel producto = productoService.findById(pedidoModel.getProductoModel().getId());
		List<Lote> lotes = loteService.traerTodoLoteDelLocalPorProducto(producto.getCodigo(), producto.getLocalModel().getId());
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

	@Override
	public List<LocalModel> obtenerLocalesCercanosConStockDisponible(String codigo, int cantidadSolicitada) {
		List<LocalModel> locales = new ArrayList<LocalModel>();
		
		for(LocalModel local : localService.getLocalesCercanos(new LocalModel())) {	
			
			ProductoModel p = productoService.findByCodigoAndLocal(codigo, local.getId());
			if(consultarStock(p.getId(), cantidadSolicitada)) {
				locales.add(local);
			}
		}
		
		return locales;
	}

	@Override
	public ArrayList<RankingModel> obtenerRanking(LocalDate fechaDesde, LocalDate fechaHasta, int localId) {
		
		return pedidoRepository.obtenerRanking(fechaDesde, fechaHasta, localId);
		
	}
	
	@Override
	public List<Pedido> obtenerPedidosPropios(int localId, int estado) {
		List<Pedido> pedidos = pedidoRepository.obtenerPedidosPropios(localId, userService.traerRol());
		pedidos = pedidos.stream()
			    .filter(x -> x.getEstado() == estado)
			    .collect(Collectors.toList());
		return pedidos;
	}

}
