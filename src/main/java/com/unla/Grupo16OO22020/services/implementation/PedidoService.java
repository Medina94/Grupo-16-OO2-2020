package com.unla.Grupo16OO22020.services.implementation;

import java.time.LocalDate;
import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

import java.io.FileNotFoundException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.unla.Grupo16OO22020.converters.PedidoConverter;
import com.unla.Grupo16OO22020.converters.PersonaConverter;
import com.unla.Grupo16OO22020.entities.Comision;
import com.unla.Grupo16OO22020.entities.Lote;
import com.unla.Grupo16OO22020.entities.Pedido;
import com.unla.Grupo16OO22020.entities.Producto;
import com.unla.Grupo16OO22020.enums.EstadoEnum;
import com.unla.Grupo16OO22020.models.DetallePedidoEmpleadoModel;
import com.unla.Grupo16OO22020.models.FacturaModel;
import com.unla.Grupo16OO22020.models.LocalModel;
import com.unla.Grupo16OO22020.models.MailModel;
import com.unla.Grupo16OO22020.models.PedidoModel;
import com.unla.Grupo16OO22020.models.PlusSueldoModel;
import com.unla.Grupo16OO22020.models.ProductoModel;
import com.unla.Grupo16OO22020.models.RankingModel;
import com.unla.Grupo16OO22020.repositories.IComisionRepository;
import com.unla.Grupo16OO22020.repositories.ILoteRepository;
import com.unla.Grupo16OO22020.repositories.IPedidoRepository;
import com.unla.Grupo16OO22020.services.IMailService;
import com.unla.Grupo16OO22020.services.IPedidoService;

import net.sf.jasperreports.engine.JRException;


@Service("pedidoService")
public class PedidoService implements IPedidoService {

	@Autowired
	@Qualifier("pedidoRepository")
	private IPedidoRepository pedidoRepository;	
	@Autowired
	@Qualifier("loteRepository")
	private ILoteRepository loteRepository;	
	@Autowired
	@Qualifier("pedidoConverter")
	private PedidoConverter pedidoConverter;
	@Autowired
	@Qualifier("personaConverter")
	private PersonaConverter personaConverter;	
	@Autowired
	@Qualifier("personaService")
	private PersonaService personaService;
	@Autowired
	@Qualifier("productoService")
	private ProductoService productoService;
	@Autowired
	@Qualifier("loteService")
	private LoteService loteService;
	@Autowired
	@Qualifier("localService")
	private LocalService localService;
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	@Autowired
	@Qualifier("comisionService")
	private ComisionService comisionService;
	@Autowired
	@Qualifier("comisionRepository")
	private IComisionRepository comisionRepository;	
	@Autowired
	@Qualifier("mailService")
	private IMailService mailService;
	@Autowired
	@Qualifier("jasperService")
	private JasperReportService jasperService;
	
	@Override	
	public List<Pedido> getAll() {
		return pedidoRepository.findAll();
	}

	@Override
	public PedidoModel insertOrUpdate(PedidoModel pedidoModel) {
		pedidoModel.setProductoModel(productoService.findById(pedidoModel.getProductoModel().getId()));
		pedidoModel.setClienteModel(personaService.clienteFindById(pedidoModel.getClienteModel().getId()));
		pedidoModel.setSolicitadorModel(personaConverter.EmpleadoEntitytoModel(userService.traerEmpleadoLogueado()));
		Pedido pedido = pedidoRepository.save(pedidoConverter.modelToEntity(pedidoModel));
		
		// envio mail de confirmado al cliente solo si el estado es == 1 (ACEPTADO)
		if(pedidoModel.getEstado() == EstadoEnum.ESTADO_ACEPTADO.getCodigo()) {
			MailModel mail = new MailModel().buildConfirmado(pedidoModel);	
			try {
				jasperService.exportReport("pdf", pedido.getId());
			} catch (FileNotFoundException | JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mailService.enviarMail(mail, true);
		}
		
		return pedidoConverter.entityToModel(pedido);
	}

	@Override
	public boolean remove(int id) {
		try {
			pedidoRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public PedidoModel findById(int id) {
		return pedidoConverter.entityToModel(pedidoRepository.findById(id));
	}

	@Override
	public boolean consultarStock(int idProducto, int cantidadSolicitada) {
		
		int stock = devolverCantidadStockDisponible(this.lotes(idProducto));
		if(stock >= cantidadSolicitada) {
			return true;
		}		
		return false;
	}
	
	public List<Lote> lotes (int idProducto) {
		 
		ProductoModel producto = productoService.findById(idProducto);
		List<Lote> lotes = loteService.traerTodoLoteDelLocalPorProducto(producto.getCodigo(), producto.getLocalModel().getId());
	    return lotes;
	}
	
	public int devolverCantidadStockDisponible(List<Lote> lotes) {
		return lotes.stream().filter(x->x.getCantidad()>0).mapToInt(x->x.getCantidad()).sum();
	}
	


	@Override
	public void actualizarStock(PedidoModel pedidoModel) {
		ProductoModel producto = productoService.findById(pedidoModel.getProductoModel().getId());
		List<Lote> lotes = loteService.traerTodoLoteDelLocalPorProducto(producto.getCodigo(), producto.getLocalModel().getId());
		int cantidadActualizada = pedidoModel.getCantidadSolicitada();
		for(Lote l: lotes) {
			 cantidadActualizada =  cantidadActualizada - l.getCantidad();
			if (cantidadActualizada >= 0) {
			    Lote lote = loteRepository.findById(l.getId());
			    lote.setCantidad(0);
				loteRepository.save(lote);
			}else if(cantidadActualizada < 0){
				Lote lote = loteRepository.findById(l.getId());
				lote.setCantidad(Math.abs(cantidadActualizada));
				loteRepository.save(lote);
			}
		}
	}

	@Override
	public void rechazarPedido(int pedidoId) {
		Pedido pedido = pedidoRepository.findById(pedidoId);		
		Lote lote = new Lote(0, pedido.getCantidadSolicitada(),LocalDate.now(),pedido.getProducto());
	    loteRepository.save(lote);
	}

	@Override
	public PedidoModel calcularTotal(int pedidoId) {
	PedidoModel pedido = pedidoConverter.entityToModel(pedidoRepository.findById(pedidoId));
	pedido.setTotal(pedido.getCantidadSolicitada() * pedido.getProductoModel().getPrecioUnitario());
	return pedido;
	}

	@Override
	public List<LocalModel> obtenerLocalesCercanosConStockDisponible(String codigo, int cantidadSolicitada) {
		List<LocalModel> locales = new ArrayList<LocalModel>();
		
		for(LocalModel local : localService.getLocalesCercanos(new LocalModel())) {	
			
			ProductoModel p = productoService.findByCodigoAndLocal(codigo, local.getId());
			if(consultarStock(p.getId(), cantidadSolicitada)) {
				locales.add(local);
			}
		}
		
		return locales;
	}

	@Override
	public ArrayList<RankingModel> obtenerRanking(LocalDate fechaDesde, LocalDate fechaHasta, int localId) {
		ArrayList <RankingModel> ranking = pedidoRepository.obtenerRanking(fechaDesde, fechaHasta, localId);
		for(Producto p : productoService.getAll()) {
			if(!ranking.stream().filter(x->x.getCodigo().equals(p.getCodigo())).findFirst().isPresent()) {
					RankingModel m = new RankingModel(0, p.getCodigo(), p.getDescripcion(), p.getImagenUrl(), p.getPrecioUnitario());
					ranking.add(m);				
			}
		}		
		return ranking;		
	}
	
	@Override
	public List<Pedido> obtenerPedidosPropios(int localId, int estado) {
		List<Pedido> pedidos = pedidoRepository.obtenerPedidosPropios(localId, userService.traerRol());
		pedidos = pedidos.stream()
			    .filter(x -> x.getEstado() == estado)
			    .collect(Collectors.toList());
		return pedidos;
	}

	@Override
	public List<PlusSueldoModel> calcularPlusPedido() {
		LocalDate start = YearMonth.now().atDay(1);
		LocalDate end   = YearMonth.now().atEndOfMonth();		
		List<PlusSueldoModel> lista= pedidoRepository.calcularPlusPedido(userService.traerEmpleadoLogueado().getLocal().getId(), start, end);
		List<Comision> listaComisiones =comisionService.getAll();
		//Collections.sort(listaComisiones, Collections.reverseOrder());
		Comision c = listaComisiones.get(0);
		for (PlusSueldoModel p : lista) {
		p.setPlus(p.getTotalPedidos() *  c.getPlusVenta() /100);	
		}
		
		return lista;
	}

	@Override
	public List<DetallePedidoEmpleadoModel> obtenerPedidosPorEmpleado(int empleado_id) {
		LocalDate start = YearMonth.now().atDay(1);
		LocalDate end   = YearMonth.now().atEndOfMonth();
		List<DetallePedidoEmpleadoModel> lista=pedidoRepository.obtenerPedidosPorEmpleado(empleado_id, start, end);
		return lista;
	}

	@Override
	public int calcularPlusTotal(int empleado) {
		LocalDate start = YearMonth.now().atDay(1);
		LocalDate end   = YearMonth.now().atEndOfMonth();
		return  pedidoRepository.obtenerPedidosPorEmpleado(empleado, start, end).stream().filter(x->x.getSubTotal()>0).mapToInt(x->x.getSubTotal()).sum();
	}
	
	public LocalDate obtenerUltimoDiaDelAño() {
		

		LocalDate now = LocalDate.now(); 
		LocalDate ultimoDia = now.with(lastDayOfYear()); 
		return ultimoDia;
	}

	public LocalDate obtenerPrimerDiaDelAño() {
		LocalDate now = LocalDate.now();
		 LocalDate primerDia = now.with(firstDayOfYear());
		 return primerDia;
	}
	
	public List<FacturaModel> generarFactura(int pedidoId){
		return pedidoRepository.generarFactura(pedidoId);
	}
}
