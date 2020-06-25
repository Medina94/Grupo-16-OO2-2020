package com.unla.Grupo16OO22020.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.Grupo16OO22020.helpers.ViewRouteHelper;
import com.unla.Grupo16OO22020.models.RankingModel;
import com.unla.Grupo16OO22020.services.ILocalService;
import com.unla.Grupo16OO22020.services.IPedidoService;
import com.unla.Grupo16OO22020.services.IProductoService;
import com.unla.Grupo16OO22020.services.implementation.JasperReportService;
import com.unla.Grupo16OO22020.services.implementation.UserService;



@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	@Autowired
	@Qualifier("localService")
	private ILocalService localService;
	@Autowired
	@Qualifier("pedidoService")
	private IPedidoService pedidoService;
	@Autowired
	@Qualifier("userService")
	private UserService userService;	
	//GET Example: SERVER/index
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.INDEX);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modelAndView.addObject("username", user.getUsername());
		modelAndView.addObject("productos", 
	    pedidoService.obtenerRanking(pedidoService.obtenerPrimerDiaDelAño(),
	    		                     pedidoService.obtenerUltimoDiaDelAño(), 0));
		modelAndView.addObject("locales", localService.getAll());		
		return modelAndView;
	}
	
	@GetMapping("/")
	public RedirectView redirectToHomeIndex() {
		return new RedirectView(ViewRouteHelper.ROUTE);
	}
	
	@GetMapping("/filtrar")
	 public ModelAndView filtrar(String desde, String hasta, int localId, int orden)
	 {	
		ModelAndView modelAndView = new ModelAndView("home/ranking");
		LocalDate fechaDesde = LocalDate.parse(desde);
		LocalDate fechaHasta = LocalDate.parse(hasta);
		ArrayList<RankingModel> productos = pedidoService.obtenerRanking(fechaDesde, fechaHasta, localId);
		
		if(orden == 1) {
			Collections.sort(productos, 
				    Comparator.comparingLong(RankingModel::getTotal).reversed());
		}else if (orden == 2){
			Collections.sort(productos, 
				    Comparator.comparingLong(RankingModel::getTotal));
		}
		else {
			modelAndView.addObject("productos", productos);
			return modelAndView;
		}
		modelAndView.addObject("productos", productos.size() >= 2 ? productos.subList(0, 2): productos);
		return modelAndView;
	 }	
	

	@GetMapping("/consultarRol")
	@ResponseBody
	 public String consultarRol()
	 {	
		return userService.traerRol();
	 }	
}
