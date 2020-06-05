package com.unla.Grupo16OO22020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
		mAV.addObject("solicitudesRealizadas", solicitudStockService.obtenerSolicitudesRealizadas());
		mAV.addObject("solicitudesRecibidas", solicitudStockService.obtenerSolicitudesRecibidas());
		return mAV;
	}
	
}
