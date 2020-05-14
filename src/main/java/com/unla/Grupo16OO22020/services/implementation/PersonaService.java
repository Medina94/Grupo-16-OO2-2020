package com.unla.Grupo16OO22020.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.Grupo16OO22020.converters.PersonaCoverter;
import com.unla.Grupo16OO22020.entities.Cliente;
import com.unla.Grupo16OO22020.entities.Empleado;
import com.unla.Grupo16OO22020.models.ClienteModel;
import com.unla.Grupo16OO22020.models.EmpleadoModel;
import com.unla.Grupo16OO22020.repositories.IClienteRepository;
import com.unla.Grupo16OO22020.repositories.IEmpleadoRepository;
import com.unla.Grupo16OO22020.services.IPersonaService;

@Service("personaService")
public class PersonaService implements IPersonaService {
	@Autowired
	private IClienteRepository clienteRepository;
	@Autowired
	private IEmpleadoRepository empleadoRepository;
	@Autowired
	private PersonaCoverter converter;

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
		return converter.ClienteEntitytoModel(clienteRepository.findById(id));
	}

	@Override
	public EmpleadoModel empleadoFindById(int id) {
		return converter.EmpleadoEntitytoModel(empleadoRepository.findById(id));
	}

	@Override
	public ClienteModel clienteInsertOrUpdate(ClienteModel modelo) {
		Cliente cliente = clienteRepository.save(converter.ClienteModelToEntity(modelo));
		return converter.ClienteEntitytoModel(cliente);
	}

	@Override
	public EmpleadoModel empleadoInsertOrUpdate(EmpleadoModel modelo) {
		Empleado empleado = empleadoRepository.save(converter.EmpleadoModelToEntity(modelo));
		return converter.EmpleadoEntitytoModel(empleado);
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

}
