package com.grupo.listaespera.util;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

public class EmailSendGrid {
  public static void sendEmail(String email, int posicion,Long reservaId) throws IOException {
    Email from = new Email("alvllnkvl@gmail.com");
    String subject = "Lista de espera";
    Email to = new Email(email);
    Content content = new Content("text/html",
    		"<h2>Tu posicion en la fila es:"+posicion+"</h2>"
    		+ "<h4><a href='http://localhost:8080/reserva/deshabilitar/"+reservaId+"'>Cancelar</a></h4>");
    Mail mail = new Mail(from, subject, to, content);

    SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
    Request request = new Request();
    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sg.api(request);
      System.out.println(response.getStatusCode());
      //System.out.println(response.getBody());
      //System.out.println(response.getHeaders());
    } catch (IOException ex) {
      throw ex;
    }
  }
}

