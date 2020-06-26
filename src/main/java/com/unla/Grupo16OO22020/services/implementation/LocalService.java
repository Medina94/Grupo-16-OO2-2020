package com.unla.Grupo16OO22020.services.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo16OO22020.converters.LocalConverter;
import com.unla.Grupo16OO22020.entities.Empleado;
import com.unla.Grupo16OO22020.entities.Local;
import com.unla.Grupo16OO22020.entities.SolicitudStock;
import com.unla.Grupo16OO22020.enums.EstadoEnum;
import com.unla.Grupo16OO22020.models.LocalModel;
import com.unla.Grupo16OO22020.models.ProductoModel;
import com.unla.Grupo16OO22020.repositories.ILocalRepository;
import com.unla.Grupo16OO22020.services.ILocalService;
import com.unla.Grupo16OO22020.services.ILoteService;
import com.unla.Grupo16OO22020.services.IPedidoService;
import com.unla.Grupo16OO22020.services.IProductoService;
import com.unla.Grupo16OO22020.services.ISolicitudStockService;




@Service("localService")
public class LocalService implements ILocalService{

	@Autowired
	@Qualifier("localRepository")
	private ILocalRepository localRepository;
	
	@Autowired
	@Qualifier("localConverter")
	private LocalConverter localConverter;
	
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	
	@Autowired
	@Qualifier("userService")
	private  UserService userService; 
	
	@Autowired
	@Qualifier("loteService")
	private ILoteService loteService;
	
	@Autowired
	@Qualifier("pedidoService")
	private IPedidoService pedidoService;
	
	@Autowired
	@Qualifier("solicitudStockService")
	private ISolicitudStockService solicitudStockService;
	
	
	@Override
	public LocalModel insertOrUpdate(LocalModel LocalModel) {
		Local Local = localRepository.save(localConverter.modelToEntity(LocalModel));
		return localConverter.entityToModel(Local);
	}

	@Override
	public boolean remove(int id) {
		try {
			localRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public LocalModel findById(int id) {
		return localConverter.entityToModel(localRepository.findById(id));
	}

	@Override
	public List<LocalModel> getLocalesCercanos(LocalModel localModel) {
		List<LocalModel> lista = new ArrayList<LocalModel>();
		lista = localesCercanos(localModel, getAll());
		return lista;
	}
	
	public static double distanciaCoord(double lat1, double lng1, double lat2, double lng2) {
		double radioTierra = 6371; //en kilómetros
		double dLat = Math.toRadians(lat2 - lat1);
		double dLng = Math.toRadians(lng2 - lng1);
		double sindLat = Math.sin(dLat / 2);
		double sindLng = Math.sin(dLng / 2);
		double va1 = Math.pow(sindLat, 2)
		+ Math.pow(sindLng, 2) * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
		double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
		return radioTierra * va2;
		}

	private List<LocalModel> localesCercanos(LocalModel localModel, List<Local> locales){
		List<LocalModel> lista = new ArrayList<>();
		for(Local local : locales) {
			LocalModel modelo = localConverter.entityToModel(local);
			if(modelo.getId() != localModel.getId()) {
				modelo.setDistanciaDelOrigen(distanciaCoord(localModel.getLatitud(), localModel.getLongitud(), modelo.getLatitud(), modelo.getLongitud()));	
				lista.add(modelo);
			}
		}
		Collections.sort(lista);
		return lista;
	}
	@Override
	public List<Local> localesConProducto(int idProducto) {
		Empleado empleado = userService.traerEmpleadoLogueado();
		ProductoModel producto = productoService.findById(idProducto);
		List<Local> locales = new ArrayList<>();
		
		for(Local local : localRepository.getByStock(producto.getCodigo())) {
			if(local.getId() != empleado.getLocal().getId()) {
				locales.add(local);
			}
		}
		return locales;
	}
	
	@Override
	public List<LocalModel> getLocalesConStock(ProductoModel producto, int cantidadSolicitada, int cantidadLocales){
		List<LocalModel> locales = localesCercanos(localConverter.entityToModel(userService.traerEmpleadoLogueado().getLocal()), localesConProducto(producto.getId()));//(localConverter.entityToModel(userService.traerEmpleadoLogueado().getLocal()));
		List<LocalModel> localConStock = new ArrayList<>();
		
		for(LocalModel local : locales) {
			ProductoModel prod = productoService.findByCodigoAndLocal(producto.getCodigo(), local.getId());
			//boolean estado= solicitudStockService.traerSolicitudesRecibidasLocalPedido(local.getId()).isEmpty();
			int calculo=pedidoService.cantidadStock(prod.getId(), cantidadSolicitada)-(cantidadSolicitada+cantidadTotalProductosPendientes(local.getId()));
			//if(calculo>=0) {
			if(calculo>=0&&pedidoService.consultarStock(prod.getId(), cantidadSolicitada)) {
				localConStock.add(local);
			}
		}
		if(cantidadLocales == 0 || cantidadLocales >= localConStock.size()) {
			return localConStock;
		}else {
			return localConStock.subList(0, cantidadLocales);
		}
	}

	@Override
	public List<Local> getAll() {
		return localRepository.findAll();
		
	}

	@Override
	public List<Local> traerLocalPorRol() {
		return localRepository.traerTodo(userService.traerEmpleadoLogueado().getLocal().getId(), userService.traerRol());
	
	}
	
	public int cantidadTotalProductosPendientes(int localId) { //trae todas las solicitudes pendientes del local que tiene stock para sacar la cantidad total y posteriormente hacer la resta
		List<SolicitudStock>lista=solicitudStockService.traerSolicitudesRecibidasLocalPedido(localId);
		int total=0;
		for (SolicitudStock solicitudStock : lista) {
			total=total+solicitudStock.getPedido().getCantidadSolicitada();
		}
		return total;
	}
	
}
