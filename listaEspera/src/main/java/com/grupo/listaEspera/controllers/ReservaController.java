package com.grupo.listaEspera.controllers;

import java.util.Random;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grupo.listaEspera.models.Reserva;
import com.grupo.listaEspera.models.User;
import com.grupo.listaEspera.services.ReservaService;
import com.grupo.listaEspera.services.UserService;

@RestController
public class ReservaController {
	private UserService userService;
	private ReservaService reservaService;
	
	
	public ReservaController(UserService userService, ReservaService reservaService) {
	
		this.userService = userService;
		this.reservaService = reservaService;
	}
	//metodo temporal de prueba
	@RequestMapping(value="/reserva/crear/usuario/{email}",method=RequestMethod.POST)
	public String crearReserva(@Valid @ModelAttribute ("reserva") Reserva reserva, BindingResult result,
			@PathVariable("email") String email) {
		
		if(result.hasErrors()) {
			return "error creando reserva";
		}
		User user=userService.findByEmail(email);
		reserva.setUser(user);
		reserva.setEstado(true);
		Random Num_Orden = new Random();
    	int minNumber = 100000;
		int random = Num_Orden.nextInt(minNumber) + 1;
		reserva.setNumeroReserva((long) random);
		reservaService.createOrUpdateReserva(reserva);
		return "reserva creada";
		 
	}
	
}
