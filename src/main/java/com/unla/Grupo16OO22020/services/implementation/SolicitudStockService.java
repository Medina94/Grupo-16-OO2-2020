package com.unla.Grupo16OO22020.services.implementation;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo16OO22020.converters.LocalConverter;
import com.unla.Grupo16OO22020.converters.PedidoConverter;
import com.unla.Grupo16OO22020.converters.ProductoConverter;
import com.unla.Grupo16OO22020.converters.SolicitudStockConverter;
import com.unla.Grupo16OO22020.entities.Comision;
import com.unla.Grupo16OO22020.entities.Empleado;
import com.unla.Grupo16OO22020.entities.Local;
import com.unla.Grupo16OO22020.entities.Pedido;
import com.unla.Grupo16OO22020.entities.Notificacion;
import com.unla.Grupo16OO22020.entities.SolicitudStock;
import com.unla.Grupo16OO22020.models.DetallePedidoEmpleadoModel;
import com.unla.Grupo16OO22020.enums.EstadoEnum;
import com.unla.Grupo16OO22020.models.MailModel;
import com.unla.Grupo16OO22020.models.PedidoModel;
import com.unla.Grupo16OO22020.models.PlusSueldoModel;
import com.unla.Grupo16OO22020.models.SolicitudStockModel;
import com.unla.Grupo16OO22020.repositories.INotificacionRepository;
import com.unla.Grupo16OO22020.repositories.ISolicitudStockRepository;
import com.unla.Grupo16OO22020.services.ILocalService;
import com.unla.Grupo16OO22020.services.IMailService;
import com.unla.Grupo16OO22020.services.IPedidoService;
import com.unla.Grupo16OO22020.services.IPersonaService;
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
	@Autowired
	@Qualifier("notificacionRepository")
	private INotificacionRepository notificacionRepository;
	@Autowired
	@Qualifier("mailService")
	private IMailService mailService;
	@Autowired
	@Qualifier("personaService")
	private IPersonaService personaService;
	@Autowired
	@Qualifier("comisionService")
	private ComisionService comisionService;
	
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
		
		// obtengo los empleados del local al que realizo el pedido para enviarles la notificacion por mail
		List<Empleado> empleadosLocal = personaService.traerEmpleadosLocal(local.getId());
		for(Empleado colaborador : empleadosLocal) {
			MailModel mail = new MailModel().buildMailColaborador(colaborador.getMail());
			mailService.enviarMail(mail, false);
		}
		// envio un mail al cliente con la notificacion de pedido pendiente
		mailService.enviarMail(new MailModel().buildPendiente(pedidoModel), false);
		
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
		Notificacion noti = new Notificacion(0, soli.getSolicitador());
		notificacionRepository.save(noti);
		solicitudStockRepository.save(soli);
		
		// envio mail con el estado del pedido al solicitante
		mailService.enviarMail(new MailModel().buildMailSolicitante(soli.getSolicitador().getMail()), false);
		
		// envio mail con la confirmacion del stock al cliente
		mailService.enviarMail(new MailModel().buildConfirmado(p), false);
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
		
		// envio mail con el estado del pedido al solicitante
		mailService.enviarMail(new MailModel().buildMailSolicitante(soli.getSolicitador().getMail()), false);
				
		// envio mail con la confirmacion del stock al cliente
		mailService.enviarMail(new MailModel().buildRechazado(p), false);
		
	    return true;
	}

	@Override
	public int consultarNotificaciones() {
		int cantidad = obtenerSolicitudesRecibidas(EstadoEnum.ESTADO_PENDIENTE.getCodigo()).size();
		return cantidad;
	}

	@Override
	public int consultarNotificacionesConfirmadas() {
		Empleado empleado = userService.traerEmpleadoLogueado();
		int cantidad = notificacionRepository.traerNotificacionesPropias(empleado.getId()).size();
		return cantidad;
	}
	
	
	
	@Override
	public List<DetallePedidoEmpleadoModel> obtenerSolicitudesConfirmadasPorEmpleado(int empleadoId) {
		LocalDate start = YearMonth.now().atDay(1);
		LocalDate end   = YearMonth.now().atEndOfMonth();
		List<DetallePedidoEmpleadoModel> lista=solicitudStockRepository.obtenerSolicitudesConfirmadasPorEmpleado(empleadoId, start, end);
		return lista;
	}

	@Override
	public List<PlusSueldoModel> calcularPlusSolicitar() {
		LocalDate inicio = YearMonth.now().atDay(1);
		LocalDate fin   = YearMonth.now().atEndOfMonth();
		List<PlusSueldoModel> lista=solicitudStockRepository.calcularPlusSolicitar(userService.traerEmpleadoLogueado().getLocal().getId(), inicio, fin);
		List<Comision> listaComisiones =comisionService.getAll();
		Comision comision=listaComisiones.get(0);
		for (PlusSueldoModel plus : lista) {
			plus.setPlusSolicitudRealizada(plus.getTotalPedidos() * comision.getPlusPedido()/100);
		}
		return lista;
	}
	@Override
	public List<DetallePedidoEmpleadoModel> obtenerSolicitudesCedidasAOtroEmpleado(int empleadoId) {
		LocalDate start = YearMonth.now().atDay(1);
		LocalDate end   = YearMonth.now().atEndOfMonth();
		List<DetallePedidoEmpleadoModel> lista=solicitudStockRepository.obtenerSolicitudesCedidasAOtroEmpleado(empleadoId, start, end);
		return lista;
	}

	@Override
	public List<PlusSueldoModel> calcularPlusCeder() {
		LocalDate inicio = YearMonth.now().atDay(1);
		LocalDate fin   = YearMonth.now().atEndOfMonth();
		List<PlusSueldoModel> lista=solicitudStockRepository.calcularPlusCeder(userService.traerEmpleadoLogueado().getLocal().getId(), inicio, fin);
		List<Comision> listaComisiones =comisionService.getAll();
		Comision comision=listaComisiones.get(0);
		for (PlusSueldoModel plus : lista) {
			plus.setPlusSolicitudRecibida(plus.getTotalPedidos() * comision.getPlusCeder()/100);
		}
		return lista;
	}

	@Override
	public int calcularPlusTotalSolicitado(int empleado) {
		LocalDate start = YearMonth.now().atDay(1);
		LocalDate end   = YearMonth.now().atEndOfMonth();
		return solicitudStockRepository.obtenerSolicitudesConfirmadasPorEmpleado(empleado, start, end).stream().filter(x->x.getSubTotal()>0).mapToInt(x->x.getSubTotal()).sum();
	}
	
	@Override
	public int calcularPlusTotalCeder(int empleado) {
		LocalDate start = YearMonth.now().atDay(1);
		LocalDate end   = YearMonth.now().atEndOfMonth();
		return solicitudStockRepository.obtenerSolicitudesCedidasAOtroEmpleado(empleado, start, end).stream().filter(x->x.getSubTotal()>0).mapToInt(x->x.getSubTotal()).sum();
	}
	
	public List<PlusSueldoModel> listarReportePlus(){
		
		List<PlusSueldoModel> ventasPropias = pedidoService.calcularPlusPedido();		
		List<PlusSueldoModel> solicitudesAceptadas = this.calcularPlusCeder();
		List<PlusSueldoModel> solicitudesRealizadas = this.calcularPlusSolicitar();
		
		List<PlusSueldoModel> plusSueldo = ventasPropias;
//		Map<Object, List<PlusSueldoModel>> agrupoPorEmpleado =
//				reportesSueldo.stream().collect(Collectors.groupingBy(w -> w.getEmpleadoId()));
//		
		for(PlusSueldoModel p : solicitudesRealizadas) {
			if(plusSueldo.stream().filter(x->x.getEmpleadoId() == p.getEmpleadoId()).findFirst().isPresent()) {
				plusSueldo.stream().filter(x->x.getEmpleadoId() == p.getEmpleadoId()).findFirst().get().setPlusSolicitudRealizada(p.getPlusSolicitudRealizada());
			}else {
				plusSueldo.add(p);
			}
			
		}
		for(PlusSueldoModel p : solicitudesAceptadas) {
			if(plusSueldo.stream().filter(x->x.getEmpleadoId() == p.getEmpleadoId()).findFirst().isPresent()) {
				plusSueldo.stream().filter(x->x.getEmpleadoId() == p.getEmpleadoId()).findFirst().get().setPlusSolicitudRecibida(p.getPlusSolicitudRecibida());
			
			}else {
				plusSueldo.add(p);
			}		
			
		}
		
		for(PlusSueldoModel plus : plusSueldo) {
			plus.setSueldoTotal(plus.getSueldo() + plus.getPlus() + plus.getPlusSolicitudRealizada() + plus.getPlusSolicitudRecibida());
		}
		
		return plusSueldo;
	}
}
