package com.unla.Grupo16OO22020.enums;

/**
 * Enum con el asunto y mensaje del mail que corresponda
 * @author Cristian
 *
 */
public enum AsuntoMailEnum {
	ASUNTO_MAIL_CLIENTE_CONFIRMADO(1, "Pedido confirmado","Tu pedido fue realizado con exito"),
	ASUNTO_MAIL_CLIENTE_PENDIENTE(2, "Pedido pendiente","Actualmente no disponemos con el stock necesario, se realizara el pedido a otro local"),
	ASUNTO_MAIL_CLIENTE_RECHAZADO(3, "Pedido rechazado","No fue posible conseguir el stock del producto solicitado"),
	ASUNTO_MAIL_SOLICITANTE(4, "Estado de solicitud", "Tu pedido fue respondido, verifica tus notificaciones"),
	ASUNTO_MAIL_COLABORADOR(5, "Solicitud recibida", "Tu local ha recibido una solicitud de stock, verifica tus notificaciones para responder el pedido");
	
	private int codigo;
	private String asunto;
	private String mensaje;
	
	private AsuntoMailEnum() {}
	
	private AsuntoMailEnum(int codigo, String asunto, String mensaje) {
		this.codigo = codigo;
		this.asunto = asunto;
		this.mensaje = mensaje;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	
	
}
