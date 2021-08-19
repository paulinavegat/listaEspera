package com.grupo.listaespera.services;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.grupo.listaespera.models.Reserva;


@Service
public class EmailService {
	
	@Autowired
	JavaMailSender javaMailSender;

	public String sendEmail(Reserva reserva,int posicion) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		String htmlMsg = "<h2>Tu posicion en la fila es:"+posicion+"</h2>"
	    		+ "<h4><a href='http://localhost:8080/reserva/deshabilitar/"+reserva.getId()+"'>Cancelar</a></h4>";
		//mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/
		helper.setText(htmlMsg, true); // Use this or above line.
		helper.setTo(reserva.getUser().getEmail());
		helper.setSubject("Lista espera");
		helper.setFrom("alvaro.llancavil@gmail.com");
		javaMailSender.send(mimeMessage);
		return "correo enviado";
	}
	
	public String sendEmailwithAttachment() {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			
			MimeMessageHelper messageHelper = 
					new MimeMessageHelper(message, true);
			
			messageHelper.setFrom("");
			messageHelper.setTo("");
			messageHelper.setSubject("Test Subject");
			messageHelper.setText("Test Body");
			
			File file = new File("Path To File");
			
			messageHelper.addAttachment(file.getName(), file);
			
			javaMailSender.send(message);
			
			return "Mail sent successfully";
			
		} catch (Exception e) {
			return "Mail sent failed";
		}
	}
}