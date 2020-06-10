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

@Controller
@RequestMapping("/reporteSueldo")
public class ReporteSueldoController {

	@Autowired
	@Qualifier("pedidoService")
	private IPedidoService pedidoService;
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView("reporteSueldo/index");
		List<PlusSueldoModel> lista = pedidoService.calcularPlusPedido();
		mAV.addObject("reporte", lista);			
		return mAV;
	}
	
	@GetMapping("/detallePedido")
	public ModelAndView DetallePedido(int empleadoId) {
		ModelAndView mAV = new ModelAndView("reporteSueldo/detallePedido");
		mAV.addObject("detalle", pedidoService.obtenerPedidosPorEmpleado(empleadoId));			
		return mAV;
	}
	
	@GetMapping("/calcularPlusTotal")
	@ResponseBody
	 public int calcularPlusTotal(int empleado)
	 {				
		return pedidoService.calcularPlusTotal(empleado);
	 }
	
}
