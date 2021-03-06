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
import com.unla.Grupo16OO22020.models.ProductoModel;
import com.unla.Grupo16OO22020.services.ILocalService;
import com.unla.Grupo16OO22020.services.IProductoService;
import com.unla.Grupo16OO22020.services.implementation.UserService;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	
	@Autowired
	@Qualifier("localService")
	private ILocalService localService;
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PRODUCTO_INDEX);		
		mAV.addObject("productos", productoService.traerTodoProductoDeLocal(userService.traerEmpleadoLogueado().getLocal().getId()));
		return mAV;
	}
	
	@GetMapping("/crear")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PRODUCTO_CREAR);
		mAV.addObject("producto", new ProductoModel());
		mAV.addObject("locales", localService.getAll());
		return mAV;
	}
	
	@PostMapping("/crear")
	public RedirectView create(@ModelAttribute("producto") ProductoModel ProductoModel) {		
		productoService.insertOrUpdate(productoService.codigoProducto(ProductoModel));
		return new RedirectView(ViewRouteHelper.PRODUCTO_ROOT);
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PRODUCTO_ACTUALIZAR);
		mAV.addObject("producto", productoService.findById(id));
		mAV.addObject("locales", localService.getAll());
		return mAV;
	}
	
	@PostMapping("/actualizar")
	public RedirectView update(@ModelAttribute("producto") ProductoModel ProductoModel) {
		productoService.insertOrUpdate(ProductoModel);
		return new RedirectView(ViewRouteHelper.PRODUCTO_ROOT);
	}
	
	@PostMapping("/eliminar/{id}")
	public RedirectView delete(@PathVariable("id") int id) {		
		ProductoModel p = productoService.findById(id);
        p.setEliminado(true);
        productoService.insertOrUpdate(p);
		return new RedirectView(ViewRouteHelper.PRODUCTO_ROOT);
	}
}
