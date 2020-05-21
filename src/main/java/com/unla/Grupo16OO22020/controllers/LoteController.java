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
import com.unla.Grupo16OO22020.models.LoteModel;
import com.unla.Grupo16OO22020.services.ILocalService;
import com.unla.Grupo16OO22020.services.ILoteService;
import com.unla.Grupo16OO22020.services.IProductoService;

@Controller
@RequestMapping("/lote")
public class LoteController {
	@Autowired
	@Qualifier("loteService")
	private ILoteService loteService;
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	
	
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LOTE_INDEX);
		mAV.addObject("lotes", loteService.getAll());		
		return mAV;
	}
	
	@GetMapping("/crear")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LOTE_CREAR);
		mAV.addObject("productos", productoService.getAll());
		mAV.addObject("lote", new LoteModel());
		return mAV;
	}
	
	@PostMapping("/crear")
	public RedirectView create(@ModelAttribute("lote") LoteModel LoteModel) {
		loteService.insertOrUpdate(LoteModel);
		return new RedirectView(ViewRouteHelper.LOTE_ROOT);
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LOTE_ACTUALIZAR);
		mAV.addObject("lote", loteService.findById(id));
		mAV.addObject("productos", productoService.getAll());
		return mAV;
	}
	
	@PostMapping("/actualizar")
	public RedirectView update(@ModelAttribute("lote") LoteModel LoteModel) {
		loteService.insertOrUpdate(LoteModel);		
		return new RedirectView(ViewRouteHelper.LOTE_ROOT);
	}
	
	@PostMapping("/eliminar/{id}")
	public RedirectView delete(@PathVariable("id") int id) {
		loteService.remove(id);
		return new RedirectView(ViewRouteHelper.LOTE_ROOT);
	}
}
