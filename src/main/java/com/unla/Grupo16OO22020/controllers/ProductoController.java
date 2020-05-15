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

import com.unla.Grupo16OO22020.models.ProductoModel;
import com.unla.Grupo16OO22020.services.IProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView("producto/index");
		mAV.addObject("productos", productoService.getAll());
		return mAV;
	}
	
	@GetMapping("/crear")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView("producto/crear");
		mAV.addObject("producto", new ProductoModel());
		return mAV;
	}
	
	@PostMapping("/crear")
	public RedirectView create(@ModelAttribute("producto") ProductoModel ProductoModel) {
		productoService.insertOrUpdate(ProductoModel);
		return new RedirectView("/producto");
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView("/producto/actualizar");
		mAV.addObject("producto", productoService.findById(id));
		return mAV;
	}
	
	@PostMapping("/actualizar")
	public RedirectView update(@ModelAttribute("producto") ProductoModel ProductoModel) {
		productoService.insertOrUpdate(ProductoModel);
		return new RedirectView("/producto");
	}
	
	@PostMapping("/eliminar/{id}")
	public RedirectView delete(@PathVariable("id") int id) {
		productoService.remove(id);
		return new RedirectView("/producto");
	}
}