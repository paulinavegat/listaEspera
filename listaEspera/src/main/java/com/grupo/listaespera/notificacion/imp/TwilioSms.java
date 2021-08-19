package com.grupo.listaespera.notificacion.imp;

import com.grupo.listaespera.models.Reserva;
import com.grupo.listaespera.notificacion.INotificacion;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class TwilioSms implements INotificacion {
	public final String ACCOUNT_SID =System.getenv("TWILIO_ACCOUNT_SID");
	public final String AUTH_TOKEN =System.getenv("TWILIO_AUTH_TOKEN");
	@Override
	public String notificar(Reserva reserva, int posicion) throws Exception {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Message message = Message.creator(
				new com.twilio.type.PhoneNumber(reserva.getUser().getNumTelefono()),
				new com.twilio.type.PhoneNumber("+19728330080"),
				"Tu posicion en la fila es: "+posicion)
				.create();
		return message.toString();
	}
}
