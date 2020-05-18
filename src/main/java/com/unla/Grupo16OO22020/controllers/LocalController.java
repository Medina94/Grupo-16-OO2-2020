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

import com.unla.Grupo16OO22020.models.LocalModel;
import com.unla.Grupo16OO22020.services.ILocalService;

@Controller
@RequestMapping("/local")
public class LocalController {

	@Autowired
	@Qualifier("localService")
	private ILocalService localService;
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView("local/index");			
		mAV.addObject("locales", localService.getAll());
		return mAV;
	}
	
	@GetMapping("/consultarLocal/{id}")
	public ModelAndView index(@PathVariable ("id") int id) {
		ModelAndView mAV = new ModelAndView("local/localesCercanos");	
		LocalModel localModel = localService.findById(id); 
		mAV.addObject("locales", localService.getLocalesCercanos(localModel));
		return mAV;
	}
	
	@GetMapping("/crear")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView("local/crear");
		mAV.addObject("local", new LocalModel());
		return mAV;
	}
	
	@PostMapping("/crear")
	public RedirectView create(@ModelAttribute("local") LocalModel LocalModel) {
		localService.insertOrUpdate(LocalModel);
		return new RedirectView("/local");
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView("/local/actualizar");
		mAV.addObject("local", localService.findById(id));
		return mAV;
	}
	
	@PostMapping("/actualizar")
	public RedirectView update(@ModelAttribute("local") LocalModel LocalModel) {
		localService.insertOrUpdate(LocalModel);
		return new RedirectView("/local");
	}
	
	@PostMapping("/eliminar/{id}")
	public RedirectView delete(@PathVariable("id") int id) {
		localService.remove(id);
		return new RedirectView("/local");
	}
	
	
}
