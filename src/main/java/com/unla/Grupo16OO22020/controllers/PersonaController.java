package com.unla.Grupo16OO22020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.Grupo16OO22020.models.ClienteModel;
import com.unla.Grupo16OO22020.models.EmpleadoModel;
import com.unla.Grupo16OO22020.services.IPersonaService;

@Controller
@RequestMapping("/persona")
public class PersonaController {
	@Autowired
	private IPersonaService personaService;
	
	@GetMapping("/empleado/all")
	public ModelAndView mostrarEmpleados() {
		ModelAndView mAV = new ModelAndView("empleado/mostrar");
		mAV.addObject("empleados", personaService.getAllEmpleado());
		return mAV;
	}
	
	@GetMapping("empleado/{id}")
	public ModelAndView empleadoGetById(@PathVariable ("id") int id) {
		ModelAndView mAV = new ModelAndView("empleado/datos");
		mAV.addObject("empleado", personaService.empleadoFindById(id));
		return mAV;
	}
	
	@GetMapping("empleado/crear")
	public ModelAndView createEmpleado() {
		ModelAndView mAV = new ModelAndView("empleado/crear"); 
		mAV.addObject("empleado", new EmpleadoModel());
		return mAV;
	}
	
	@PostMapping("empleado/crear")
	public RedirectView createEmpleado(@ModelAttribute EmpleadoModel modelo) {
		personaService.empleadoInsertOrUpdate(modelo); 
		return new RedirectView("");
	}
	
	@PostMapping("/empleado/eliminar/{id}")
	public RedirectView deleteEmpleado(@PathVariable("id") int id) {
		personaService.EmpleadoRemove(id);
		return new RedirectView("/empleado");
	}
	//***************************************************

	@GetMapping("/cliente/all")
	public ModelAndView mostrarClientes() {
		ModelAndView mAV = new ModelAndView("cliente/mostrar");
		mAV.addObject("clientes", personaService.getAllCliente());
		return mAV;
	}
	
	@GetMapping("cliente/{id}")
	public ModelAndView clienteGetById(@PathVariable ("id") int id) {
		ModelAndView mAV = new ModelAndView("cliente/datos");
		mAV.addObject("empleado", personaService.clienteFindById(id));
		return mAV;
	}
	
	@GetMapping("cliente/crear")
	public ModelAndView createCliente() {
		ModelAndView mAV = new ModelAndView("cliente/crear"); 
		mAV.addObject("empleado", new EmpleadoModel());
		return mAV;
	}
	
	@PostMapping("cliente/crear")
	public RedirectView createCliente(@ModelAttribute ClienteModel modelo) {
		personaService.clienteInsertOrUpdate(modelo); 
		return new RedirectView("");
	}
	
	@PostMapping("/cliente/eliminar/{id}")
	public RedirectView deleteCliente(@PathVariable("id") int id) {
		personaService.clienteRemove(id);
		return new RedirectView("/cliente");
	}
}
