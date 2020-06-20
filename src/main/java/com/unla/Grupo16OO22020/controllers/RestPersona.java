/*package com.unla.Grupo16OO22020.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unla.Grupo16OO22020.entities.Cliente;
import com.unla.Grupo16OO22020.services.ILocalService;
import com.unla.Grupo16OO22020.services.IPersonaService;
import com.unla.Grupo16OO22020.services.IProductoService;
import com.unla.Grupo16OO22020.services.ReadFileService;

@RestController
@RequestMapping("/rest")
public class RestPersona {
	@Autowired
	private IPersonaService personaService;
	@Autowired
	@Qualifier("localService")
	private ILocalService localService;
	@Autowired
	private ReadFileService readFile;
	
	@Autowired
	private IProductoService productoService;
	
	//@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	@RequestMapping(value = "/empleados", method = RequestMethod.GET)
	public List<Cliente> mostrarEmpleados() {
		
		return personaService.getAllCliente();
	}
	
//	@RequestMapping(value="/consultarLocal/{id}", method = RequestMethod.GET)
//	public List<LocalModel> index(@PathVariable ("id") int id) {
//		//ModelAndView mAV = new ModelAndView("local/index");	
//		//mAV.addObject("locales", localService.getLocalCercano(localModel));
//		LocalModel model = localService.findById(id);
//		return localService.getLocalesCercanos(model);
//	}
	
	@RequestMapping(value="/insertar", method = RequestMethod.GET)
	public String index() {
		//ModelAndView mAV = new ModelAndView("local/index");	
		//mAV.addObject("locales", localService.getLocalCercano(localModel));
		readFile.insertarEmpleado();
		return "VERIFICAR SI SE CARGARON";
	}
	

	
}
*/