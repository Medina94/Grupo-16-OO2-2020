package com.unla.Grupo16OO22020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.unla.Grupo16OO22020.enums.EstadoEnum;
import com.unla.Grupo16OO22020.services.ISolicitudStockService;

@Controller
@RequestMapping("/solicitudStock")
public class SolicitudStockController {
	@Autowired
	@Qualifier("solicitudStockService")
	private ISolicitudStockService solicitudStockService;
	
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView("solicitudStock/index");
		mAV.addObject("solicitudesRealizadas", solicitudStockService.obtenerSolicitudesRealizadas(EstadoEnum.ESTADO_PENDIENTE.getCodigo()));
		mAV.addObject("solicitudesRecibidas", solicitudStockService.obtenerSolicitudesRecibidas(EstadoEnum.ESTADO_PENDIENTE.getCodigo()));
		return mAV;
	}
	
	@GetMapping("/filtrarSolicitudes")
	public ModelAndView filtrarSolicitudes(int estado) {
		ModelAndView mAV = new ModelAndView("solicitudStock/solicitudPartial");
		mAV.addObject("solicitudesRealizadas", solicitudStockService.obtenerSolicitudesRealizadas(estado));
		mAV.addObject("solicitudesRecibidas", solicitudStockService.obtenerSolicitudesRecibidas(estado));
		return mAV;
	}
	
	@GetMapping("/rechazarSolicitud")
	@ResponseBody
	 public boolean rechazarSolicitud(int solicitud)
	 {	
		return solicitudStockService.rechazarSolicitudStock(solicitud);		
	 }	
	
	@GetMapping("/aceptarSolicitud")
	@ResponseBody
	 public boolean aceptarSolicitud(int solicitud)
	 {	
		return solicitudStockService.aceptarSolicitudStock(solicitud);
	 }
	
	 @GetMapping("/verificarSolicitudes")
	 @ResponseBody
	 public int verificarSolicitudes() {
		 return solicitudStockService.consultarNotificaciones();
	 }
	 
	 @GetMapping("/verificarNotificaciones")
	 @ResponseBody
	 public int verificarNotificaciones() {
		 return solicitudStockService.consultarNotificacionesConfirmadas();
	 }
}
