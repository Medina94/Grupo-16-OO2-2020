package com.unla.Grupo16OO22020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.Grupo16OO22020.helpers.ViewRouteHelper;
import com.unla.Grupo16OO22020.models.LocalidadModel;
import com.unla.Grupo16OO22020.services.ILocalidadService;

@Controller
@RequestMapping("/localidad")
public class LocalidadController {

	@Autowired
	@Qualifier("localidadService")
	private ILocalidadService localidadService;
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView("localidad/index");
		mAV.addObject("localidades", localidadService.getAll());
		return mAV;
	}
	
	@GetMapping("/crear")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView("localidad/crear");
		mAV.addObject("localidad", new LocalidadModel());
		return mAV;
	}
	
	@PostMapping("/crear")
	public RedirectView create(@ModelAttribute("localidad") LocalidadModel LocalidadModel) {
		localidadService.insertOrUpdate(LocalidadModel);
		return new RedirectView("/localidad");
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView("/localidad/actualizar");
		mAV.addObject("localidad", localidadService.findById(id));
		return mAV;
	}
	
	@PostMapping("/actualizar")
	public RedirectView update(@ModelAttribute("localidad") LocalidadModel LocalidadModel) {
		localidadService.insertOrUpdate(LocalidadModel);
		return new RedirectView("/localidad");
	}
	
	@PostMapping("/eliminar/{id}")
	public RedirectView delete(@PathVariable("id") int id) {
		localidadService.remove(id);
		return new RedirectView("/localidad");
	}
}
