/*package com.unla.Grupo16OO22020.services.implementation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo16OO22020.converters.PersonaConverter;
import com.unla.Grupo16OO22020.entities.Empleado;
import com.unla.Grupo16OO22020.entities.User;
import com.unla.Grupo16OO22020.entities.UserRole;
import com.unla.Grupo16OO22020.helpers.LeerTxt;
import com.unla.Grupo16OO22020.models.ClienteModel;
import com.unla.Grupo16OO22020.models.EmpleadoModel;
import com.unla.Grupo16OO22020.models.LocalModel;
import com.unla.Grupo16OO22020.models.LoteModel;
import com.unla.Grupo16OO22020.models.ProductoModel;
import com.unla.Grupo16OO22020.repositories.IRoleRepository;
import com.unla.Grupo16OO22020.repositories.IUserRepository;
import com.unla.Grupo16OO22020.services.ReadFileService;

@Service("readFileService")
public class ReadFileServiceImpl implements ReadFileService{
	@Autowired
	private PersonaService personaService;
	@Autowired
	@Qualifier("personaConverter")
	private PersonaConverter personaConverter;
	@Autowired
	private LocalService localService;
	@Autowired
	private ProductoService productoService;
	@Autowired
	private LoteService loteService;
	@Autowired
	private UserService userService;
	@Autowired
	@Qualifier("userRepository")
    private IUserRepository userRepository;
	@Autowired
	@Qualifier("roleRepository")
	private IRoleRepository roleRepository;
	
	@Autowired
	private LeerTxt leerTxt;
	
	private static final String PATH_EMPLEADO = "src\\main\\resources\\text\\empleado";
	private static final String PATH_LOCAL = "src\\main\\resources\\text\\local";
	private static final String PATH_PRODUCTO = "src\\main\\resources\\text\\producto";
	private static final String PATH_LOTE = "src\\main\\resources\\text\\lote";
	private static final String PATH_USER = "src\\main\\resources\\text\\user";
	private static final String PATH_ROLE = "src\\main\\resources\\text\\role";
	
	@SuppressWarnings("unchecked")
	@Override
	//@PostConstruct
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
			localService.insertOrUpdate(em);
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
	//@PostConstruct
	public void insertarLote() {
		List<LoteModel> lista = new ArrayList<>();
		lista = (List<LoteModel>)(Object)leerTxt.leer(PATH_LOTE);
		for(LoteModel em : lista) {
			loteService.insertOrUpdate(em);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	//@PostConstruct
	public void crearUser() {
		List<User> lista = new ArrayList<>();
		lista = (List<User>)(Object)leerTxt.leer(PATH_USER);
		for(User em : lista) {
			userRepository.save(em);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	//@PostConstruct
	public void crearUserRole() {
		List<UserRole> lista = new ArrayList<>();
		lista = (List<UserRole>)(Object)leerTxt.leer(PATH_ROLE);
		for(UserRole em : lista) {
			roleRepository.save(em);
		}
	}





	
}

*/