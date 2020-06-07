package com.unla.Grupo16OO22020.services;

import java.util.List;

import com.unla.Grupo16OO22020.entities.SolicitudStock;
import com.unla.Grupo16OO22020.models.PedidoModel;
import com.unla.Grupo16OO22020.models.SolicitudStockModel;

public interface ISolicitudStockService {
	public SolicitudStock findById(int id);
	
	public SolicitudStock crearSolicitud(PedidoModel pedido, int idLocal);
	
	public List<SolicitudStock> obtenerSolicitudesRecibidas(int estado);
	
	public List<SolicitudStock> obtenerSolicitudesRealizadas(int estado);
	
	public SolicitudStockModel traerSolicitudStock(int solicitud);
	
	public boolean aceptarSolicitudStock(int soli);
	
	public boolean rechazarSolicitudStock(int soli);
}
