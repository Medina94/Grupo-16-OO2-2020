package com.unla.Grupo16OO22020.services.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.Grupo16OO22020.helpers.LeerTxt;
import com.unla.Grupo16OO22020.models.ClienteModel;
import com.unla.Grupo16OO22020.services.ReadFileService;

@Service("readFileService")
public class ReadFileServiceImpl implements ReadFileService{
	@Autowired
	private PersonaService personaService;
	private static final String PATH_CLIENTE = "src\\main\\resources\\text\\cliente";
	
	@SuppressWarnings("unchecked")
	@Override
	@PostConstruct
	public void insertarEmpleado() {
		List<ClienteModel> lista = new ArrayList<>();
		lista = (List<ClienteModel>)(Object)LeerTxt.leer(PATH_CLIENTE);
		for(ClienteModel em : lista) {
			personaService.clienteInsertOrUpdate(em);
		}
	}
	
}

