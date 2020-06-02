package com.unla.Grupo16OO22020.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.Grupo16OO22020.models.LocalModel;
import com.unla.Grupo16OO22020.models.PedidoModel;
import com.unla.Grupo16OO22020.repositories.IUserRepository;
import com.unla.Grupo16OO22020.services.ILocalService;
import com.unla.Grupo16OO22020.services.IPedidoService;
import com.unla.Grupo16OO22020.services.IPersonaService;
import com.unla.Grupo16OO22020.services.IProductoService;
import com.unla.Grupo16OO22020.services.implementation.PersonaService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
	@Autowired
	@Qualifier("pedidoService")
	private IPedidoService pedidoService;
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	@Autowired
	@Qualifier("personaService")
	private IPersonaService clienteService;
	@Autowired
	@Qualifier("personaService")
	private IPersonaService empleadoService;
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	@Autowired
	@Qualifier("localService")
	private ILocalService localService;
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView("pedido/index");
		mAV.addObject("pedidos", pedidoService.getAll());			
		return mAV;
	}
	
	@GetMapping("/crear")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView("pedido/crear");
		mAV.addObject("productos", productoService.getAll());
		mAV.addObject("clientes", clienteService.getAllCliente());	
		mAV.addObject("pedido", new PedidoModel());
		return mAV;
	}
	
	@PostMapping("/crear")
	public RedirectView create(@ModelAttribute("pedido") PedidoModel pedidoModel) {		
		pedidoService.insertOrUpdate(pedidoModel);
		pedidoService.actualizarStock(pedidoModel);
		return new RedirectView("/pedido");
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView("/pedido/actualizar");
		mAV.addObject("pedido", pedidoService.findById(id));
		mAV.addObject("productos", productoService.getAll());
		mAV.addObject("clientes", clienteService.getAllCliente());	
		mAV.addObject("empleados", empleadoService.getAllEmpleado());
		return mAV;
	}
	
	@PostMapping("/actualizar")
	public RedirectView update(@ModelAttribute("pedido") PedidoModel pedidoModel) {
		pedidoService.insertOrUpdate(pedidoModel);		
		return new RedirectView("/pedido");
	}
	
	@PostMapping("/eliminar/{id}")
	public RedirectView delete(@PathVariable("id") int id) {
		pedidoService.rechazarPedido(id);
		pedidoService.remove(id);
		return new RedirectView("/pedido");
	}
	
	@GetMapping("/consultarStock")
	@ResponseBody
	 public String getConsultarStock(int idProducto, int cantidadSolicitada)
	 {	
		boolean res = pedidoService.consultarStock(idProducto, cantidadSolicitada);
	      if(res) {
	    	return "ok";  
	      }
	      	return "error";
	 }	
	
	@GetMapping("/visualizarPedido")
	public ModelAndView visualizar(int pedidoId) {
		ModelAndView mAV = new ModelAndView("pedido/visualizarPedido");	
		PedidoModel pedido = pedidoService.calcularTotal(pedidoId);
		mAV.addObject("pedido", pedido);
		return mAV;
	}
	
	@GetMapping("/solicitarStock")
	public ModelAndView solicitarStockALocal(int idProducto, int cantidadSolicitada) {
		List<LocalModel> localesConStock = new ArrayList<>();
		ModelAndView mAV = new ModelAndView("pedido/solicitarStock");
		//mAV.addObject("localesConStock", localService.localesConStock(idProducto, cantidadSolicitada));
		//mAV.addObject("empleados", empleadoService.obtenerEmpleados());
		return mAV;
	}
	
	@PostMapping("/solicitar")
	public RedirectView solicitar(@ModelAttribute ("pedido") PedidoModel pedido) {
		
		
		return new RedirectView("/pedido");
	}
}
