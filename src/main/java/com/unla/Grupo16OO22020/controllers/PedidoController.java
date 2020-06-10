package com.unla.Grupo16OO22020.controllers;

import java.time.LocalDate;

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

import com.unla.Grupo16OO22020.converters.ProductoConverter;
import com.unla.Grupo16OO22020.enums.EstadoEnum;
import com.unla.Grupo16OO22020.models.PedidoModel;
import com.unla.Grupo16OO22020.models.ProductoModel;
import com.unla.Grupo16OO22020.repositories.IUserRepository;
import com.unla.Grupo16OO22020.services.ILocalService;
import com.unla.Grupo16OO22020.services.IPedidoService;
import com.unla.Grupo16OO22020.services.IPersonaService;
import com.unla.Grupo16OO22020.services.IProductoService;
import com.unla.Grupo16OO22020.services.ISolicitudStockService;
import com.unla.Grupo16OO22020.services.implementation.UserService;

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
	@Autowired
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;
	@Autowired
	@Qualifier("solicitudStockService")
	private ISolicitudStockService solicitudStockService;
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView("pedido/index");
		mAV.addObject("pedidos", pedidoService.obtenerPedidosPropios(userService.traerEmpleadoLogueado().getLocal().getId(), 1));			
		return mAV;
	}
	
	@GetMapping("/crear")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView("pedido/crear");
		mAV.addObject("productos", productoService.traerTodoProductoDeLocal(userService.traerEmpleadoLogueado().getLocal().getId()));
		mAV.addObject("clientes", clienteService.getAllCliente());	
		mAV.addObject("pedido", new PedidoModel());
		return mAV;
	}
	
	@PostMapping("/crear")
	public RedirectView create(@ModelAttribute("pedido") PedidoModel pedidoModel) {		
		pedidoModel.setFecha(LocalDate.now());
		if(pedidoService.consultarStock(pedidoModel.getProductoModel().getId(), pedidoModel.getCantidadSolicitada())) {
			pedidoModel.setEstado(EstadoEnum.ESTADO_ACEPTADO.getCodigo());
			pedidoService.insertOrUpdate(pedidoModel);
			pedidoService.actualizarStock(pedidoModel);
		}else {
			pedidoModel.setEstado(EstadoEnum.ESTADO_PENDIENTE.getCodigo());
			PedidoModel p = pedidoService.insertOrUpdate(pedidoModel);
			solicitudStockService.crearSolicitud(p, pedidoModel.getIdLocalSolicitado());
			
		}
		
		return new RedirectView("/pedido");
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView("/pedido/actualizar");
		mAV.addObject("pedido", pedidoService.findById(id));
		mAV.addObject("productos", productoService.traerTodoProductoDeLocal(userService.traerEmpleadoLogueado().getLocal().getId()));
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
	public ModelAndView solicitarStockALocal(int idProducto, int cantidadSolicitada, int cantidadLocales) {
		ModelAndView mAV = new ModelAndView("pedido/solicitarStock");
		ProductoModel producto = productoService.findById(idProducto);
		mAV.addObject("localesConStock", localService.getLocalesConStock(producto, cantidadSolicitada, cantidadLocales));
		return mAV;
	}
	@GetMapping("/filtrarEstados")
	public ModelAndView filtrarEstados(int estado) {
		ModelAndView mAV = new ModelAndView("pedido/pedidosEstado");
		mAV.addObject("pedidos", pedidoService.obtenerPedidosPropios(userService.traerEmpleadoLogueado().getLocal().getId(), estado));			
		return mAV;
	}
	
	
	
}
