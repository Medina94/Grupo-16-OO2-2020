package com.unla.Grupo16OO22020.services.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.Grupo16OO22020.helpers.LeerTxt;
import com.unla.Grupo16OO22020.models.ClienteModel;
import com.unla.Grupo16OO22020.models.LocalModel;
import com.unla.Grupo16OO22020.models.LoteModel;
import com.unla.Grupo16OO22020.models.ProductoModel;
import com.unla.Grupo16OO22020.services.ReadFileService;

@Service("readFileService")
public class ReadFileServiceImpl implements ReadFileService{
	@Autowired
	private PersonaService personaService;
	@Autowired
	private LocalService localService;
	@Autowired
	private ProductoService productoService;
	@Autowired
	private LoteService loteService;
	@Autowired
	private LeerTxt leerTxt;
	
	private static final String PATH_EMPLEADO = "src\\main\\resources\\text\\empleado";
	private static final String PATH_LOCAL = "src\\main\\resources\\text\\local";
	private static final String PATH_PRODUCTO = "src\\main\\resources\\text\\producto";
	private static final String PATH_LOTE = "src\\main\\resources\\text\\lote";
	
	@SuppressWarnings("unchecked")
	@Override
	public void insertarEmpleado() {
		List<ClienteModel> lista = new ArrayList<>();
		lista = (List<ClienteModel>)(Object)leerTxt.leer(PATH_EMPLEADO);
		for(ClienteModel em : lista) {
			
			personaService.clienteInsertOrUpdate(em);
		
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	//@PostConstruct
	public void insertarLocal() {
		List<LocalModel> lista = new ArrayList<>();
		lista = (List<LocalModel>)(Object)leerTxt.leer(PATH_LOCAL);
		for(LocalModel em : lista) {
			
			LocalModel l = localService.insertOrUpdate(em);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@PostConstruct
	public void insertarProducto() {
		List<ProductoModel> lista = new ArrayList<>();
		lista = (List<ProductoModel>)(Object)leerTxt.leer(PATH_PRODUCTO);
		for(ProductoModel em : lista) {
			productoService.insertOrUpdate(em);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void insertarLote() {
		List<LoteModel> lista = new ArrayList<>();
		lista = (List<LoteModel>)(Object)leerTxt.leer(PATH_LOTE);
		for(LoteModel em : lista) {
			loteService.insertOrUpdate(em);
		}
	}
}

