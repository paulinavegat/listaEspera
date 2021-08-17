package com.grupo.listaespera.util;

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

public class EmailService {
	
	public static boolean enviarNotificacion(String correo,int posicion,Long id) {
		final String username = "correo@gmail.com";
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

	    try {

	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress("correo@gmail.com"));
	        message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse(correo));
	        message.setSubject("Lista de espera");
//	        message.setText("Dear Mail Crawler,"
//	                + "\n\n No spam to my email, please!");
	        String msg = "Tu posicion en la fila es : "+posicion+"<br>"
	        		+ "<a href='http://miIp:8080/reserva/cambiarstatus/"+id+"'>Cancelar reserva</a>";

	        MimeBodyPart mimeBodyPart = new MimeBodyPart();
	        mimeBodyPart.setContent(msg, "text/html");

	        Multipart multipart = new MimeMultipart();
	        multipart.addBodyPart(mimeBodyPart);

	        message.setContent(multipart);

	        Transport.send(message);


	    } catch (MessagingException e) {
	        throw new RuntimeException(e);
	        
	    }
	    return true;
	}
	
}

