package com.unla.Grupo16OO22020.services.implementation;

import java.util.ArrayList;
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
import com.unla.Grupo16OO22020.models.LocalModel;
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
	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Override
	public List<Cliente> getAllCliente() {
		return clienteRepository.getAllCliente();
	}

	@Override
	public List<Empleado> getAllEmpleado() {
		return empleadoRepository.getAllEmpleado();
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
		String email = modelo.getNombre().charAt(0) +  modelo.getApellido() +"@unla.com";
		modelo.setMail(email.toLowerCase());
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
		Empleado e = empleadoRepository.findByDni(modelo.getDni());
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		char inicial =  e.getNombre().charAt(0);
		String nombreDeUsuario = inicial +  e.getApellido();
		User usuario = new User(nombreDeUsuario.toLowerCase(), String.valueOf(e.getDni()), true, e);
		usuario.setPassword(pe.encode(usuario.getPassword()));
		List<UserRole> roles = new ArrayList<UserRole>();	
		userRepository.save(usuario);
		UserRole rol = new UserRole ();
		if (modelo.isEsGerente()) {			
			rol = new UserRole (0, usuario,"ROLE_GERENTE");			
		}else {
			rol = new UserRole (0, usuario,"ROLE_EMPLEADO");
		}
		
		roleRepository.save(rol);
	}

	@Override
	public String validarEmpleado(int dni) {
		Empleado e = empleadoRepository.findByDni(dni);		
		String mensaje = "El empleado se guardo con exito";
		if (e != null && e.getDni() == dni) {
			mensaje = "Error, el empleado ya está registrado"; 
		}
		return mensaje;
	}
	
	@Override
	public String validarCliente(int dni) {
		Cliente c = clienteRepository.findByDni(dni);
		String mensaje = "El cliente se guardo con exito";
		if (c != null && c.getDni() == dni) {
			mensaje = "Error, el cliente ya está registrado"; 
		}
		return mensaje;
	}

	@Override
	public List<EmpleadoModel> obtenerEmpleados(int id) {
		Empleado empleadoLogueado = userService.traerEmpleadoLogueado();
		//LocalModel local = localService.findById(id);
		List<EmpleadoModel> listaEmpleados = new ArrayList<>();
		
		for(Empleado empleado : getAllEmpleado()) {
			if(empleado.getId() != empleadoLogueado.getId() && empleado.getLocal().getId() == id) {
				listaEmpleados.add(personaConverter.EmpleadoEntitytoModel(empleado));
			}
		}
		return listaEmpleados;
	}

}