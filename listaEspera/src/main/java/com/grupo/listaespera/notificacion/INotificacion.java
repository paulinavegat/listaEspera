package com.grupo.listaespera.notificacion;

import com.grupo.listaespera.models.Reserva;

public interface INotificacion {
	public abstract String notificar(Reserva reserva,int posicion) throws Exception;
}
