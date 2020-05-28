package com.unla.Grupo16OO22020.services.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.unla.Grupo16OO22020.converters.PersonaConverter;
import com.unla.Grupo16OO22020.entities.Cliente;
import com.unla.Grupo16OO22020.entities.Empleado;
import com.unla.Grupo16OO22020.entities.User;
import com.unla.Grupo16OO22020.entities.UserRole;
import com.unla.Grupo16OO22020.models.ClienteModel;
import com.unla.Grupo16OO22020.models.EmpleadoModel;
import com.unla.Grupo16OO22020.repositories.IClienteRepository;
import com.unla.Grupo16OO22020.repositories.IEmpleadoRepository;
import com.unla.Grupo16OO22020.repositories.IRoleRepository;
import com.unla.Grupo16OO22020.repositories.IUserRepository;
import com.unla.Grupo16OO22020.services.IPersonaService;

@Service("personaService")
public class PersonaService implements IPersonaService {
	
	@Autowired
	@Qualifier("localService")
	private LocalService localService;
	@Autowired
	@Qualifier("clienteRepository")
	private IClienteRepository clienteRepository;
	@Autowired
	@Qualifier("roleRepository")
	private IRoleRepository roleRepository;
	@Autowired
	@Qualifier("empleadoRepository")
	private IEmpleadoRepository empleadoRepository;
	@Autowired
	@Qualifier("personaConverter")
	private PersonaConverter personaConverter;
	@Autowired
	@Qualifier("userRepository")
    private IUserRepository userRepository;   

	@Override
	public List<Cliente> getAllCliente() {
		return clienteRepository.findAll();
	}

	@Override
	public List<Empleado> getAllEmpleado() {
		return empleadoRepository.findAll();
	}

	@Override
	public ClienteModel clienteFindById(int id) {
		return personaConverter.ClienteEntitytoModel(clienteRepository.findById(id));
	}

	@Override
	public EmpleadoModel empleadoFindById(int id) {
		return personaConverter.EmpleadoEntitytoModel(empleadoRepository.findById(id));
	}
	
	

	@Override
	public ClienteModel clienteInsertOrUpdate(ClienteModel modelo) {
		Cliente cliente = clienteRepository.save(personaConverter.ClienteModelToEntity(modelo));
		return personaConverter.ClienteEntitytoModel(cliente);
	}

	@Override
	public EmpleadoModel empleadoInsertOrUpdate(EmpleadoModel modelo) {
		modelo.setLocalModel(localService.findById(modelo.getLocalModel().getId()));
		Empleado empleado = empleadoRepository.save(personaConverter.EmpleadoModelToEntity(modelo));
		return personaConverter.EmpleadoEntitytoModel(empleado);
	}

	@Override
	public boolean clienteRemove(int id) {
		try {
			clienteRepository.deleteById(id);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean EmpleadoRemove(int id) {
		try {
			empleadoRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void crearUsuario(EmpleadoModel modelo) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		char inicial = modelo.getNombre().charAt(0);
		String nombreDeUsuario = inicial + modelo.getApellido();
		User usuario = new User(nombreDeUsuario, String.valueOf(modelo.getDni()), true, personaConverter.EmpleadoModelToEntity(modelo));
		usuario.setPassword(pe.encode(usuario.getPassword()));
		List<UserRole> roles = new ArrayList<UserRole>();				
		userRepository.save(usuario);
//		UserRole rol = roleRepository.findByrole("ROLE_GERENTE");
//		if (modelo.isEsGerente()) {			
//			roles.add(rol);			
//		}
//		rol = roleRepository.findByrole("ROLE_EMPLEADO");
//		roles.add(rol);
//		usuario.setUserRoles(new HashSet<>(roles));
//		userRepository.save(usuario);
	}

}