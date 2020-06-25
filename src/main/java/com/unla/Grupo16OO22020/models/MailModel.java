package com.unla.Grupo16OO22020.models;

import com.unla.Grupo16OO22020.enums.AsuntoMailEnum;

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
	private int id;
	
	public MailModel() {}
	
	public MailModel(int id, String emisor, String receptor, String asunto, String mensaje) {
		this.emisor = emisor;
		this.receptor = receptor;
		this.asunto = asunto;
		this.mensaje = mensaje;
		this.id = id;
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
	
	// Defino constructores para enviar mail en las 3 posibles respuestas al pedido del cliente
	public MailModel buildConfirmado(PedidoModel pedido) {
		this.emisor = "emisor";
		this.receptor = pedido.getClienteModel().getMail();
		this.mensaje = AsuntoMailEnum.ASUNTO_MAIL_CLIENTE_CONFIRMADO.getMensaje();
		this.asunto = AsuntoMailEnum.ASUNTO_MAIL_CLIENTE_CONFIRMADO.getAsunto();
		this.id = pedido.getId();
		return this;
	}
	
	public MailModel buildPendiente(PedidoModel pedido) {
		this.emisor = "emisor";
		this.receptor = pedido.getClienteModel().getMail();
		this.mensaje = AsuntoMailEnum.ASUNTO_MAIL_CLIENTE_PENDIENTE.getMensaje();
		this.asunto = AsuntoMailEnum.ASUNTO_MAIL_CLIENTE_PENDIENTE.getAsunto();
		return this;
	}
	
	public MailModel buildRechazado(PedidoModel pedido) {
		this.emisor = "emisor";
		this.receptor = pedido.getClienteModel().getMail();
		this.mensaje = AsuntoMailEnum.ASUNTO_MAIL_CLIENTE_RECHAZADO.getMensaje();
		this.asunto = AsuntoMailEnum.ASUNTO_MAIL_CLIENTE_RECHAZADO.getAsunto();
		this.id = pedido.getId();
		return this;
	}
	//--------------------------------------------------------------------------
	/**
	 * Este metodo se debe llamar cuando un colaborador responde a la solicitud de stock
	 * @param pedido
	 * @return
	 */
	public MailModel buildMailSolicitante(String mailSolicitante) {
		this.emisor = "emisor";
		this.receptor = mailSolicitante;
		this.mensaje = AsuntoMailEnum.ASUNTO_MAIL_SOLICITANTE.getMensaje();
		this.asunto = AsuntoMailEnum.ASUNTO_MAIL_SOLICITANTE.getAsunto();
		return this;
	}
	
	/**
	 * Este metodo deberia de usarse para enviar el mail a cada uno de los empleados
	 *  que pertenecen al local al que se realiza la solicitud de stock
	 * @param mailColaborador
	 * @return
	 */
	public MailModel buildMailColaborador(String mailColaborador) {
		this.emisor = "emisor";
		this.receptor = mailColaborador;
		this.mensaje = AsuntoMailEnum.ASUNTO_MAIL_COLABORADOR.getMensaje();
		this.asunto = AsuntoMailEnum.ASUNTO_MAIL_COLABORADOR.getAsunto();
		return this;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
