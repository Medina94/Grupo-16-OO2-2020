package com.unla.Grupo16OO22020.services.implementation;

import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo16OO22020.converters.ProductoConverter;
import com.unla.Grupo16OO22020.entities.Producto;
import com.unla.Grupo16OO22020.models.ProductoModel;
import com.unla.Grupo16OO22020.repositories.IProductoRepository;
import com.unla.Grupo16OO22020.services.IProductoService;


@Service("productoService")
public class ProductoService implements IProductoService {

	@Autowired
	@Qualifier("productoRepository")
	private IProductoRepository productoRepository;
	
	@Autowired
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;
	
	@Override
	public List<Producto> getAll() {
		return productoRepository.getAll();
	}
	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Override
	public ProductoModel insertOrUpdate(ProductoModel ProductoModel) {
		Producto Producto = productoRepository.save(productoConverter.modelToEntity(ProductoModel));
		return productoConverter.entityToModel(Producto);
	}

	@Override
	public boolean remove(int id) {
		try {
			productoRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public ProductoModel findById(int id) {
		return productoConverter.entityToModel(productoRepository.findById(id));
	}

	@Override
	public ProductoModel findByCodigoAndLocal(String codigo, int localId) {
		return productoConverter.entityToModel(productoRepository.findByCodigoAndLocal(codigo, localId));
	}
	
	@Override
	public List<Producto> traerTodoProductoDeLocal(int localId) {
		return productoRepository.traerTodoProductoDeLocal(localId);
		
	}
	
	public ProductoModel codigoProducto(ProductoModel producto) {
		StringTokenizer palabras = new StringTokenizer(producto.getDescripcion());
		String codigo = null;
		String codigo2 = null;
		String codigo3 = "";
		while(palabras.hasMoreTokens()) {
			codigo2 = palabras.nextToken();
			codigo = codigo2.substring(0,1);
			codigo3 = codigo3 + codigo;
	}producto.setCodigo(codigo3);
		return producto;
}
	
	
	
}
