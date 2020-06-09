package com.unla.Grupo16OO22020.services.implementation;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo16OO22020.converters.LocalConverter;
import com.unla.Grupo16OO22020.converters.PedidoConverter;
import com.unla.Grupo16OO22020.converters.ProductoConverter;
import com.unla.Grupo16OO22020.converters.SolicitudStockConverter;
import com.unla.Grupo16OO22020.entities.Empleado;
import com.unla.Grupo16OO22020.entities.Local;
import com.unla.Grupo16OO22020.entities.Pedido;
import com.unla.Grupo16OO22020.entities.SolicitudStock;
import com.unla.Grupo16OO22020.enums.EstadoEnum;
import com.unla.Grupo16OO22020.models.PedidoModel;
import com.unla.Grupo16OO22020.models.SolicitudStockModel;
import com.unla.Grupo16OO22020.repositories.ISolicitudStockRepository;
import com.unla.Grupo16OO22020.services.ILocalService;
import com.unla.Grupo16OO22020.services.IPedidoService;
import com.unla.Grupo16OO22020.services.IProductoService;
import com.unla.Grupo16OO22020.services.ISolicitudStockService;

@Service("solicitudStockService")
public class SolicitudStockService implements ISolicitudStockService{
	@Autowired
	@Qualifier("solicitudStockRepository")
	private ISolicitudStockRepository solicitudStockRepository;
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	@Autowired
	@Qualifier("localService")
	private ILocalService localService;
	@Autowired
	@Qualifier("localConverter")
	private LocalConverter localConverter;
	@Autowired
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;
	@Autowired
	@Qualifier("pedidoConverter")
	private PedidoConverter pedidoConverter;
	@Autowired
	@Qualifier("pedidoService")
	private IPedidoService pedidoService;
	@Autowired
	@Qualifier("solicitudStockConverter")
	private SolicitudStockConverter solicitudStockConverter;
	
	@Override
	public SolicitudStock findById(int id) {
		return solicitudStockRepository.findById(id);
	}

	@Override
	public SolicitudStock crearSolicitud(PedidoModel pedidoModel, int idLocal) {
		Empleado empleado = userService.traerEmpleadoLogueado();
		Local local = localConverter.modelToEntity(localService.findById(idLocal));
		int estado = EstadoEnum.ESTADO_PENDIENTE.getCodigo();		
		SolicitudStock solicitud = new SolicitudStock(empleado, estado, pedidoConverter.modelToEntity(pedidoModel), local);		
		return solicitudStockRepository.save(solicitud);
	}

	@Override
	public List<SolicitudStock> obtenerSolicitudesRecibidas(int estado) {
		Empleado empleado = userService.traerEmpleadoLogueado();
		List<SolicitudStock> lista = solicitudStockRepository.obtenerSolicitudesRecibidas(empleado.getLocal().getId());		
		lista = lista.stream()
			    .filter(x -> x.getEstado() == estado)
			    .collect(Collectors.toList());
		return lista;
	}

	@Override
	public List<SolicitudStock> obtenerSolicitudesRealizadas(int estado) {
		Empleado empleado = userService.traerEmpleadoLogueado();
		List<SolicitudStock> lista =  solicitudStockRepository.obtenerSolicitudesRealizadas(empleado.getLocal().getId());		
		lista = lista.stream()
			    .filter(x -> x.getEstado() == estado)
			    .collect(Collectors.toList());		
		return lista;
	}

	@Override
	public SolicitudStockModel traerSolicitudStock(int solicitud) {
		return solicitudStockConverter.entityToModel(solicitudStockRepository.findById(solicitud));		 
	}

	@Override
	public boolean aceptarSolicitudStock(int solicitud) {
		SolicitudStock soli = solicitudStockRepository.findById(solicitud);
		PedidoModel p = pedidoService.findById(soli.getPedido().getId());		
		p.setEstado(EstadoEnum.ESTADO_ACEPTADO.getCodigo());
		soli.setEstado(EstadoEnum.ESTADO_ACEPTADO.getCodigo());
		soli.setColaborador(userService.traerEmpleadoLogueado());
		pedidoService.actualizarStock(p);
		pedidoService.insertOrUpdate(p);
		solicitudStockRepository.save(soli);
	    return true;
	}

	@Override
	public boolean rechazarSolicitudStock(int solicitud) {
		SolicitudStock soli = solicitudStockRepository.findById(solicitud);
		PedidoModel p = pedidoService.findById(soli.getPedido().getId());
		p.setEstado(EstadoEnum.ESTADO_RECHAZADO.getCodigo());
		soli.setEstado(EstadoEnum.ESTADO_RECHAZADO.getCodigo());
		soli.setColaborador(userService.traerEmpleadoLogueado());
		pedidoService.insertOrUpdate(p);
		solicitudStockRepository.save(soli);
	    return true;
	}	
}
