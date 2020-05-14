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
import com.unla.Grupo16OO22020.services.ILocalService;
import com.unla.Grupo16OO22020.services.IPersonaService;

@Controller
@RequestMapping("/persona")
public class PersonaController {
	@Autowired
	private IPersonaService personaService;
	@Autowired
	private ILocalService localService;
	
	@GetMapping("/empleado/all")
	public ModelAndView mostrarEmpleados() {
		ModelAndView mAV = new ModelAndView("empleado/mostrar");
		mAV.addObject("empleados", personaService.getAllEmpleado());
		return mAV;
	}
	
	
	@GetMapping("empleado/{id}")
	public ModelAndView empleadoGetById(@PathVariable ("id") int id) {
		ModelAndView mAV = new ModelAndView("empleado/actualizar");
		EmpleadoModel empleado = personaService.empleadoFindById(id);
		mAV.addObject("empleado", empleado);
		mAV.addObject("local", localService.findById(empleado.getId()));
		return mAV;
	}
	@PostMapping("empleado/actualizar")
	public RedirectView modificarEmpleado(@ModelAttribute("empleado") EmpleadoModel modelo) {
		personaService.empleadoInsertOrUpdate(modelo);
		return new RedirectView("/persona/empleado/all");
	}
	
	@GetMapping("empleado/crear")
	public ModelAndView createEmpleado() {
		ModelAndView mAV = new ModelAndView("empleado/crear"); 
		mAV.addObject("empleado", new EmpleadoModel());
		mAV.addObject("locales", localService.getAll());
		return mAV;
	}
	
	@PostMapping("empleado/crear")
	public RedirectView createEmpleado(@ModelAttribute("empleado") EmpleadoModel modelo) {
		personaService.empleadoInsertOrUpdate(modelo); 
		return new RedirectView("empleado/mostrar");
	}
	
	@PostMapping("/empleado/eliminar/{id}")
	public RedirectView deleteEmpleado(@PathVariable("id") int id) {
		personaService.EmpleadoRemove(id);
		return new RedirectView("/persona/empleado/all");
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
		ModelAndView mAV = new ModelAndView("cliente/actualizar");
		mAV.addObject("empleado", personaService.clienteFindById(id));
		return mAV;
	}
	
	@PostMapping("cliente/actualizar")
	public RedirectView modificarCliente(@ModelAttribute("cliente") ClienteModel modelo) {
		personaService.clienteInsertOrUpdate(modelo);
		return new RedirectView("/persona/cliente/all");
	}
	
	@GetMapping("cliente/crear")
	public ModelAndView createCliente() {
		ModelAndView mAV = new ModelAndView("cliente/crear"); 
		mAV.addObject("cliente", new ClienteModel());
		return mAV;
	}
	
	@PostMapping("cliente/crear")
	public RedirectView createCliente(@ModelAttribute("cliente") ClienteModel modelo) {
		personaService.clienteInsertOrUpdate(modelo); 
		return new RedirectView("/persona/cliente/all");
	}
	
	@PostMapping("/cliente/eliminar/{id}")
	public RedirectView deleteCliente(@PathVariable("id") int id) {
		personaService.clienteRemove(id);
		return new RedirectView("/persona/cliente/all");
	}
}
