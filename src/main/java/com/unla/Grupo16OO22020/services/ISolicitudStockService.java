package com.unla.Grupo16OO22020.services;

import java.util.List;

import com.unla.Grupo16OO22020.entities.SolicitudStock;
import com.unla.Grupo16OO22020.models.DetallePedidoEmpleadoModel;
import com.unla.Grupo16OO22020.models.PedidoModel;
import com.unla.Grupo16OO22020.models.PlusSueldoModel;
import com.unla.Grupo16OO22020.models.SolicitudStockModel;

public interface ISolicitudStockService {
	public SolicitudStock findById(int id);
	
	public SolicitudStock crearSolicitud(PedidoModel pedido, int idLocal);
	
	public List<SolicitudStock> obtenerSolicitudesRecibidas(int estado);
	
	public List<SolicitudStock> obtenerSolicitudesRealizadas(int estado);
	
	public SolicitudStockModel traerSolicitudStock(int solicitud);
	
	public boolean aceptarSolicitudStock(int soli);
	
	public boolean rechazarSolicitudStock(int soli);
	
	public int consultarNotificaciones();
	
	public int consultarNotificacionesConfirmadas();
	
	public int consultarNotificacionesRechazadas();
	
	public List<DetallePedidoEmpleadoModel>obtenerSolicitudesConfirmadasPorEmpleado(int empleadoId);
	
	public List<PlusSueldoModel> calcularPlusSolicitar();
	
	public List<DetallePedidoEmpleadoModel> obtenerSolicitudesCedidasAOtroEmpleado(int empleadoId);

	public List<PlusSueldoModel> calcularPlusCeder();
	
	public int calcularPlusTotalSolicitado(int empleado);
	
	public int calcularPlusTotalCeder(int empleado);
	
	public boolean remove();
}
