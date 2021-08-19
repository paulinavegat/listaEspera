package com.grupo.listaespera.notificacion.imp;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.grupo.listaespera.models.Reserva;
import com.grupo.listaespera.notificacion.INotificacion;

public class SmtpEmail implements INotificacion {

	@Override
	public String notificar(Reserva reserva, int posicion) throws Exception {
		final String username = "alvllnkvl@gmail.com";
		final String password = "pass";

		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "*");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});


		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("alvllnkvl@gmail.com"));
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(reserva.getUser().getEmail()));
		message.setSubject("Lista de espera");
		//	        message.setText("Dear Mail Crawler,"
		//	                + "\n\n No spam to my email, please!");
		String msg = "Tu posicion en la fila es : "+posicion+"<br>"
				+ "<a href='http://localhost:8080/reserva/deshabilitar/"+reserva.getId()+"'>Cancelar reserva</a>";

		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(msg, "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);

		message.setContent(multipart);

		Transport.send(message);

		return "smtp email fin";
	}

}
