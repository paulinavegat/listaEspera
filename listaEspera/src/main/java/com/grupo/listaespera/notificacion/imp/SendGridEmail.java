package com.grupo.listaespera.notificacion.imp;


import com.grupo.listaespera.models.Reserva;
import com.grupo.listaespera.notificacion.INotificacion;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

public class SendGridEmail implements INotificacion {

	@Override
	public String notificar(Reserva reserva, int posicion) throws Exception {
		  Email from = new Email("alvllnkvl@gmail.com");
		    String subject = "Lista de espera";
		    Email to = new Email(reserva.getUser().getEmail());
		    Content content = new Content("text/html",
		    		"<h2>Tu posicion en la fila es:"+posicion+"</h2>"
		    		+ "<h4><a href='http://localhost:8080/reserva/deshabilitar/"+reserva.getId()+"'>Cancelar</a></h4>");
		    Mail mail = new Mail(from, subject, to, content);

		    SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		    Request request = new Request();

		    request.setMethod(Method.POST);
		    request.setEndpoint("mail/send");
		    request.setBody(mail.build());
		    Response response = sg.api(request);
			return "SendgridEmail to: "+reserva.getUser().getEmail()+" end";
		  
	}
}
