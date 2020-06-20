package com.unla.Grupo16OO22020.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.Grupo16OO22020.helpers.ViewRouteHelper;
import com.unla.Grupo16OO22020.models.ComisionModel;
import com.unla.Grupo16OO22020.models.EmpleadoModel;
import com.unla.Grupo16OO22020.models.LocalModel;
import com.unla.Grupo16OO22020.services.IComisionService;

@Controller
@RequestMapping("/comision")
public class ComisionController {

	@Autowired
	@Qualifier("comisionService")
	private IComisionService comisionService;
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView("comision/index");
		mAV.addObject("comisiones", comisionService.getAll());			
		return mAV;
	}
	
	@GetMapping("/crear")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView("comision/crear"); //cambio de index a crear por rotura
		mAV.addObject("comision", new ComisionModel());
		return mAV;
	}
	
	@PostMapping("/crear")
	public RedirectView create(@ModelAttribute("comision") ComisionModel comisionModel) {
		comisionModel.setFecha(LocalDate.now());
		comisionService.insertOrUpdate(comisionModel);
		return new RedirectView(ViewRouteHelper.COMISION_ROOT);
	}
	
	@GetMapping("/calculo")
	public ModelAndView calcular(int año,int mes,@ModelAttribute("empleado") EmpleadoModel EmpleadoModel) {
		ModelAndView mAV = new ModelAndView();
		return mAV;
	}
}
