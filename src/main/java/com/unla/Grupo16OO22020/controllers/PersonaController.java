package com.unla.Grupo16OO22020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.Grupo16OO22020.helpers.ViewRouteHelper;
import com.unla.Grupo16OO22020.models.ClienteModel;
import com.unla.Grupo16OO22020.models.EmpleadoModel;
import com.unla.Grupo16OO22020.models.ProductoModel;
import com.unla.Grupo16OO22020.services.ILocalService;
import com.unla.Grupo16OO22020.services.IPersonaService;

@Controller
@RequestMapping("/persona")
public class PersonaController {
	@Autowired
	@Qualifier("personaService")
	private IPersonaService personaService;
	@Autowired
	@Qualifier("localService")
	private ILocalService localService;
	

	
	@GetMapping("/empleado/all")
	public ModelAndView mostrarEmpleados() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.EMPLEADO_INDEX);
		mAV.addObject("empleados", personaService.getAllEmpleado());
		return mAV;
	}
	
	
	@GetMapping("empleado/{id}")
	public ModelAndView empleadoGetById(@PathVariable ("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.EMPLEADO_ACTUALIZAR);
		EmpleadoModel empleado = personaService.empleadoFindById(id);
		mAV.addObject("empleado", empleado);
		mAV.addObject("locales", localService.getAll());
		return mAV;
	}
	@PostMapping("empleado/actualizar")
	public RedirectView modificarEmpleado(@ModelAttribute("empleado") EmpleadoModel modelo) {
		personaService.empleadoInsertOrUpdate(modelo);
		return new RedirectView(ViewRouteHelper.EMPLEADO_ROOT);
	}
	
	@GetMapping("empleado/crear")
	public ModelAndView createEmpleado() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.EMPLEADO_CREAR); 
		mAV.addObject("empleado", new EmpleadoModel());
		mAV.addObject("locales", localService.getAll());
		return mAV;
	}
	
	@PostMapping("empleado/crear")
	public RedirectView createEmpleado(@ModelAttribute("empleado") EmpleadoModel modelo) {		
		personaService.empleadoInsertOrUpdate(modelo); 
		personaService.crearUsuario(modelo);
		return new RedirectView(ViewRouteHelper.EMPLEADO_ROOT);
	}
	
	@PostMapping("/empleado/eliminar/{id}")
	public RedirectView deleteEmpleado(@PathVariable("id") int id) {
		EmpleadoModel e = personaService.empleadoFindById(id);
        e.setEliminado(true);
        personaService.empleadoInsertOrUpdate(e);
		return new RedirectView(ViewRouteHelper.EMPLEADO_ROOT);
	}
	
	@GetMapping("/validarEmpleado")
	@ResponseBody
	 public String validarEmpleado(int dni)
	 {		 
	     return personaService.validarEmpleado(dni);	     
	 }	
	
	//***************************************************

	@GetMapping("/cliente/all")
	public ModelAndView mostrarClientes() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.CLIENTE_INDEX);
		mAV.addObject("clientes", personaService.getAllCliente());
		return mAV;
	}
	
	@GetMapping("cliente/{id}")
	public ModelAndView clienteGetById(@PathVariable ("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.CLIENTE_ACTUALIZAR);
		mAV.addObject("cliente", personaService.clienteFindById(id));
		return mAV;
	}
	
	@PostMapping("cliente/actualizar")
	public RedirectView modificarCliente(@ModelAttribute("cliente") ClienteModel modelo) {
		personaService.clienteInsertOrUpdate(modelo);
		return new RedirectView(ViewRouteHelper.CLIENTE_ROOT);
	}
	
	@GetMapping("cliente/crear")
	public ModelAndView createCliente() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.CLIENTE_CREAR); 
		mAV.addObject("cliente", new ClienteModel());
		return mAV;
	}
	
	@PostMapping("cliente/crear")
	public RedirectView createCliente(@ModelAttribute("cliente") ClienteModel modelo) {
		personaService.clienteInsertOrUpdate(modelo); 
		return new RedirectView(ViewRouteHelper.CLIENTE_ROOT);
	}
	
	@PostMapping("/cliente/eliminar/{id}")
	public RedirectView deleteCliente(@PathVariable("id") int id) {
		ClienteModel c = personaService.clienteFindById(id);
        c.setEliminado(true);
        personaService.clienteInsertOrUpdate(c);
		return new RedirectView(ViewRouteHelper.CLIENTE_ROOT);
	}
}
