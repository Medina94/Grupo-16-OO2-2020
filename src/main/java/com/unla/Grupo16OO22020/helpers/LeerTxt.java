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

import com.unla.Grupo16OO22020.models.ClienteModel;

public class LeerTxt {
	public static List<Object> leer(String path) {
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
				lista.add(empleadosTxt(map));
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
		int sueldo = Integer.parseInt((String) map.get("telefono"));
		int dni = Integer.parseInt((String) map.get("dni"));
		
		return new ClienteModel(0,dni,nombre, apellido, fecha, mail, sueldo, false); // seteo por default eliminado = false
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