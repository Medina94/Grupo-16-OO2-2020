package com.unla.Grupo16OO22020.models;

/**
 * Clase que establece la estructura básica de un mail
 * @author Cristian
 *
 */
public class MailModel {
	private String emisor;
	private String receptor;
	private String asunto;
	private String mensaje;
	private static final String ASUNTO = "Confirmación de pedido";
	
	public MailModel() {}
	
	public MailModel(String emisor, String receptor, String asunto, String mensaje) {
		this.emisor = emisor;
		this.receptor = receptor;
		this.asunto = asunto;
		this.mensaje = mensaje;
	}

	public String getEmisor() {
		return emisor;
	}

	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}

	public String getReceptor() {
		return receptor;
	}

	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "emisor=" + emisor + ", receptor=" + receptor + ", asunto=" + asunto + ", mensaje=" + mensaje;
	}
	
	public MailModel build(PedidoModel pedido) {
		this.emisor = "emisor";
		this.receptor = pedido.getClienteModel().getMail();
		this.mensaje = "Tu pedido fue confirmado con éxito";
		this.asunto = ASUNTO;
		return this;
	}
}
