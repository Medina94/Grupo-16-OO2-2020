package com.unla.Grupo16OO22020.services.implementation;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.unla.Grupo16OO22020.models.MailModel;
import com.unla.Grupo16OO22020.services.IMailService;
/**
 * Service para el envio de mails
 * @author Cristian
 *
 */
@Service("mailService")
public class MailServiceImpl implements IMailService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	@Async
	public boolean enviarMail(MailModel mail, boolean adjunto) {
		LOGGER.info("MailModel: {}", mail.toString());
		return enviarMailDestinatario(mail, adjunto);
	}

	private boolean enviarMailDestinatario(MailModel mail, boolean adjunto) {
		boolean send = false;
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true); // True para poder enviar adjuntos
		
			helper.setTo(mail.getReceptor());
			helper.setText(mail.getMensaje());
			helper.setSubject(mail.getAsunto());
			// Obtengo un archivo del sistema y lo adjunto en el mail
			if(adjunto) {
				//FileSystemResource file = new FileSystemResource("C:\\Users\\Cristian\\Desktop\\Plantilla Informe de Avance.pdf");
				//helper.addAttachment(file.getFilename(), file);
			}
			mailSender.send(message);
			send = true;
			LOGGER.info("Mail enviado!!");
		}catch (Exception e) {
			LOGGER.error("ERROR al enviar el mail: {}", e);
		}
		
		return send;
	}
	
}
