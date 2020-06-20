package com.unla.Grupo16OO22020.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.unla.Grupo16OO22020.models.PlusSueldoModel;
import com.unla.Grupo16OO22020.services.IPedidoService;
import com.unla.Grupo16OO22020.services.ISolicitudStockService;

@Controller
@RequestMapping("/reporteSueldo")
public class ReporteSueldoController {

	@Autowired
	@Qualifier("pedidoService")
	private IPedidoService pedidoService;
	@Autowired
	@Qualifier("solicitudStockService")
	private ISolicitudStockService solicitudStockService;
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView("reporteSueldo/index");
		mAV.addObject("reporte", pedidoService.calcularPlusPedido());
		mAV.addObject("reportePedir", solicitudStockService.calcularPlusSolicitar());
		mAV.addObject("reporteCeder", solicitudStockService.calcularPlusCeder());
		return mAV;
	}
	
	@GetMapping("/detallePedido")
	public ModelAndView DetallePedido(int empleadoId) {
		ModelAndView mAV = new ModelAndView("reporteSueldo/detallePedido");
		mAV.addObject("detalle", pedidoService.obtenerPedidosPorEmpleado(empleadoId));	
		mAV.addObject("detallePedido", solicitudStockService.obtenerSolicitudesConfirmadasPorEmpleado(empleadoId));
		mAV.addObject("cedido", solicitudStockService.obtenerSolicitudesCedidasAOtroEmpleado(empleadoId));
		return mAV;
	}
	
	@GetMapping("/calcularPlusTotal")
	@ResponseBody
	 public int calcularPlusTotal(int empleado)
	 {				
		return pedidoService.calcularPlusTotal(empleado);
	 }
	
	@GetMapping("/calcularPlusTotalSolicitado")
	@ResponseBody
	 public int calcularPlusTotalSolicitado(int empleado)
	 {				
		return solicitudStockService.calcularPlusTotalSolicitado(empleado);
	 }
	
	@GetMapping("/calcularPlusTotalCedido")
	@ResponseBody
	 public int calcularPlusTotalCedido(int empleado)
	 {				
		return solicitudStockService.calcularPlusTotalCeder(empleado);
	 }
	
}
