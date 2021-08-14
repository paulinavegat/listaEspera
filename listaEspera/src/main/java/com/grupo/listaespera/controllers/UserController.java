package com.grupo.listaespera.controllers;

import java.util.Random;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo.listaespera.models.Reserva;
import com.grupo.listaespera.models.User;
import com.grupo.listaespera.services.ReservaService;
import com.grupo.listaespera.services.UserService;

@RestController
public class UserController {
	private UserService userService;
	private ReservaService reservaService;


	public UserController(UserService userService, ReservaService reservaService) {

		this.userService = userService;
		this.reservaService = reservaService;
	}


	@PostMapping("/registro")
	public String crearUser(@Valid @ModelAttribute ("user")User user, @Valid @ModelAttribute ("reserva") Reserva reserva, BindingResult result) {
		if(result.hasErrors()) {
			return "error registro";
		}else {
			userService.createUser(user);
			Random Num_Reserva = new Random();
			int minNumber = 10000;
			int Random = Num_Reserva.nextInt(minNumber) + 1;
			reserva.setNumeroReserva(Random);
			reserva.setEstadoR(true);
			reserva.setUser(user);
			reservaService.createNuevaReserva(reserva);
			return "usuario creado y reserva creada";
		}   
	}

	
    @PostMapping("/user/crear")
    public String createUser(@Valid @ModelAttribute ("user")User user, BindingResult result) {
     if(result.hasErrors()) {
		return "error creando usuario";
	}else {
	    userService.createUser(user);
	    return "usuario creado";
	}   
  }
}

