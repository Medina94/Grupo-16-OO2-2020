package com.unla.Grupo16OO22020.helpers;

public class ViewRouteHelper {
	/**** Views ****/
	//HOME
	public final static String INDEX = "home/index";
	
	// EMPLEADO
	public final static String EMPLEADO_INDEX = "empleado/mostrar";
	public final static String EMPLEADO_CREAR = "empleado/crear";
	public final static String EMPLEADO_ACTUALIZAR = "empleado/actualizar";
	
	// CLIENTE
	public final static String CLIENTE_INDEX = "cliente/mostrar";
	public final static String CLIENTE_CREAR = "cliente/crear";
	public final static String CLIENTE_ACTUALIZAR = "cliente/actualizar";
	
	// LOCAL
	public final static String LOCAL_INDEX = "local/index";
	public final static String LOCAL_CREAR = "local/crear";
	public final static String LOCAL_ACTUALIZAR = "local/actualizar";
	public final static String LOCAL_VER_CERCANOS = "local/localesCercanos";
	
	// LOTE
	public final static String LOTE_INDEX = "lote/index";
	public final static String LOTE_CREAR = "lote/crear";
	public final static String LOTE_ACTUALIZAR = "lote/actualizar";
	
	// PRODUCTO 
	public final static String PRODUCTO_INDEX = "producto/index";
	public final static String PRODUCTO_CREAR = "producto/crear";
	public final static String PRODUCTO_ACTUALIZAR = "producto/actualizar";
	
	//USER
	public final static String USER_LOGIN = "user/login";
	public final static String USER_LOGOUT = "user/logout";
	
	// COMISION
	public final static String COMISION_INDEX = "comision/index";
//	
//	/**** Redirects ****/
	public final static String ROUTE = "/index";
	public final static String DEGREE_ROOT = "/degrees/";
	public final static String EMPLEADO_ROOT = "/persona/empleado/all";
	public final static String CLIENTE_ROOT = "/persona/cliente/all";
	public final static String LOCAL_ROOT = "/local";
	public final static String LOTE_ROOT = "/lote";
	public final static String PRODUCTO_ROOT = "/producto";
	public final static String COMISION_ROOT ="/comision";
}
