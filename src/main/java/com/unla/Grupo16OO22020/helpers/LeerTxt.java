package com.unla.Grupo16OO22020.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.internal.compiler.batch.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo16OO22020.converters.PersonaConverter;
import com.unla.Grupo16OO22020.entities.Empleado;
import com.unla.Grupo16OO22020.entities.User;
import com.unla.Grupo16OO22020.entities.UserRole;
import com.unla.Grupo16OO22020.models.ClienteModel;
import com.unla.Grupo16OO22020.models.EmpleadoModel;
import com.unla.Grupo16OO22020.models.LocalModel;
import com.unla.Grupo16OO22020.models.LoteModel;
import com.unla.Grupo16OO22020.models.ProductoModel;
import com.unla.Grupo16OO22020.repositories.IUserRepository;
import com.unla.Grupo16OO22020.services.ILocalService;
import com.unla.Grupo16OO22020.services.IPersonaService;

@Service("leerTxt")
public class LeerTxt { 
	@Autowired
	@Qualifier("personaConverter")
	private PersonaConverter personaConverter;
	
	@Autowired
	@Qualifier("localService")
	private ILocalService localService;
	

	@Autowired
	@Qualifier("personaService")
	private IPersonaService personaService;
	
	@Autowired
	@Qualifier("userRepository")
    private IUserRepository userRepository;
	
	public List<Object> leer(String path) {
		
		List<Object> lista = new ArrayList<>();
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File(path);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			String linea;
			while ((linea = br.readLine()) != null) {				
				Map<String, Object> map = parse(linea);
				if(path.contains("empleado")){
					lista.add(empleadosTxt(map));
				}
				if(path.contains("local")){
					lista.add(localTxt(map));
				}
				if(path.contains("producto")){
					lista.add(productoTxt(map));
				}
				if(path.contains("user")){
					lista.add(userTxt(map));
				}
				if(path.contains("role")){
					lista.add(roleTxt(map));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	    return lista;
	}
	
	/**
	 * Generacion de un ClienteModel desde datos de un txt
	 * @param map
	 * @return
	 */
	public static ClienteModel empleadosTxt(Map<String, Object> map) {
		String nombre = (String) map.get("nombre");
		String apellido = (String) map.get("apellido");
		LocalDate fecha = aFecha((String) map.get("fecha"));
		String mail = (String) map.get("mail");
		int telefono = Integer.parseInt((String) map.get("telefono"));
		int dni = Integer.parseInt((String) map.get("dni"));
		
		
		return new ClienteModel(0,dni,nombre, apellido, fecha, mail, telefono, false);
	}
	
	public LocalModel localTxt(Map<String, Object> map) {
		int telefono = Integer.parseInt((String) map.get("telefono"));
		String direccion = (String) map.get("direccion");
		double latitud = Double.parseDouble((String) map.get("latitud"));
		double longitud = Double.parseDouble((String) map.get("longitud"));		
		
		return new LocalModel(0,telefono,direccion, latitud, longitud);
	}
	
	public ProductoModel productoTxt(Map<String, Object> map) {
		String descripcion = (String) map.get("descripcion");
		int precioUnitario = Integer.parseInt((String) map.get("precioUnitario"));	
		LocalDate fechaAlta = aFecha((String) map.get("fechaAlta"));	
		String codigo = (String) map.get("codigo");
		String imagenUrl = (String) map.get("imagenUrl");
		int localModel = Integer.parseInt((String) map.get("localModel"));
		LocalModel local = localService.findById(localModel);
	
		
		return new ProductoModel(0,descripcion,precioUnitario,local,fechaAlta,false,codigo,imagenUrl);
	}
	
	public LoteModel loteTxt(Map<String, Object> map) {
		int cantidad = (int) map.get("cantidad");
		LocalDate fechaIngreso = aFecha((String) map.get("fechaIngreso"));	
		ProductoModel productolModel = (ProductoModel) map.get("productolModel");
		
		return new LoteModel(0,cantidad,fechaIngreso,productolModel);
	}
	public User userTxt(Map<String, Object> map) {
		String username = (String) map.get("username");
		String password = (String) map.get("password");
		int empleadoModel = Integer.parseInt((String) map.get("empleadoModel"));
		EmpleadoModel empleado = personaService.empleadoFindById(empleadoModel);
		Empleado p = personaConverter.EmpleadoModelToEntity(empleado);
		
		return new User(username,password,true,p);
	}
	
	public UserRole roleTxt(Map<String, Object> map) {
		String username = (String) map.get("username");
		User usuario = userRepository.findByUsername(username);
		String rol = (String) map.get("rol");
		
		
		return new UserRole(0,usuario,rol);
	}
	/**
	 * Metodo para guardar en un Map las Key-Value de los models sacados de un txt
	 * @param linea
	 * @return
	 */
	public static Map<String, Object> parse(String linea) {
		Map<String, Object> map = new HashMap<>();
		int pos = 0;
		String key = "";
		String value = "";
		for(int i=0; i < linea.length(); i++) {
			if(linea.charAt(i) == ';') {
				String l = linea.substring(pos, i);
				String array[] = l.split("=");
				key = array[0];	
				value = array[1];
				map.put(key, value);
				pos=i+1;
			}
		}
		
		return map;
	}
	private static LocalDate aFecha(String fecha) {
		return LocalDate.parse(fecha, DateTimeFormatter.ISO_LOCAL_DATE);
	}
}