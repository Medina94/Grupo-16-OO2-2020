package com.unla.Grupo16OO22020.services;

import com.unla.Grupo16OO22020.models.MailModel;

/**
 * Service para el envio de mails
 * @author Cristian
 *
 */
public interface IMailService {
	/**
	 * Metodo utilizado para el envio de mails con adjunto
	 * @param mail
	 * @return
	 */
	public boolean enviarMailCliente(MailModel mail);
	
}
