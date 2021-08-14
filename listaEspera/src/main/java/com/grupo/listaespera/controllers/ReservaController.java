package com.grupo.listaespera.controllers;
import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grupo.listaespera.models.Reserva;
import com.grupo.listaespera.models.User;
import com.grupo.listaespera.services.ReservaService;
import com.grupo.listaespera.services.UserService;

@RestController
public class ReservaController {
	private UserService userService;
	private ReservaService reservaService;
	
	
	public ReservaController(UserService userService, ReservaService reservaService) {

		this.userService = userService;
		this.reservaService = reservaService;
	}

	@RequestMapping("/reservas")
	public  List<Reserva> reservas() {
		return reservaService.allReservas();
	}


	//nueva reserva para usuarios ya registrados
	@PostMapping("/nuevaReserva/{usuarioId}")
	public String nuevaReserva ( @Valid @ModelAttribute ("reserva") Reserva reserva, BindingResult result,
			@PathVariable("usuarioId") Long usuarioId) {
		if(result.hasErrors()) {
			return "home.jsp";
		}else {
			User user=userService.findUser(usuarioId);

			Random Num_Reserva = new Random();
			int minNumber = 10000;
			int Random = Num_Reserva.nextInt(minNumber) + 1;
			reserva.setNumeroReserva(Random);
			reserva.setEstadoR(true);
			reserva.setUser(user);
			reservaService.createNuevaReserva(reserva);
			return "creada nueva reserva";
		}   
	}


	@RequestMapping(value="/reserva/crear/usuario/{email}",method=RequestMethod.POST)
	public String crearReserva(@Valid @ModelAttribute ("reserva") Reserva reserva, BindingResult result,
			@PathVariable("email") String email) {

		if(result.hasErrors()) {
			return "error creando reserva";
		}
		User user=userService.findByEmail(email);
		reserva.setUser(user);
		reserva.setEstadoR(true);
		Random Num_Orden = new Random();
		int minNumber = 100000;
		int random = Num_Orden.nextInt(minNumber) + 1;
		reserva.setNumeroReserva(random);
		reservaService.createNuevaReserva(reserva);
		return "reserva creada";

	}



	@RequestMapping("/reserva/habilitar/{id}")
	public String activar (@PathVariable("id") Long id) {
		Reserva reserva=reservaService.findReserva(id);
		Boolean estadoR=true;
		reserva.setEstadoR(estadoR);
		reservaService.updateReserva(reserva);
		return  "reserva habilitada";
	}

	@RequestMapping("/reserva/deshabilitar/{id}")
	public String deshabilitar (@PathVariable("id") Long id) {
		Reserva reserva=reservaService.findReserva(id);
		Boolean estadoR=false;
		reserva.setEstadoR(estadoR);
		reservaService.updateReserva(reserva);
		return  "reserva deshabilitada";
	}


}
