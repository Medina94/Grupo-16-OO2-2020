package com.unla.Grupo16OO22020.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.unla.Grupo16OO22020.models.SolicitudStockModel;
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
		mAV.addObject("solicitudesRealizadas", solicitudStockService.obtenerSolicitudesRealizadas(1));
		mAV.addObject("solicitudesRecibidas", solicitudStockService.obtenerSolicitudesRecibidas(1));
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
	
}
