package com.grupo.listaespera.controllers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.grupo.listaespera.models.Reserva;
import com.grupo.listaespera.models.User;
import com.grupo.listaespera.services.ReservaService;
import com.grupo.listaespera.services.UserService;
import com.grupo.listaespera.util.ReservaResponse;

@RestController
public class ReservaController {
	private UserService userService;
	private ReservaService reservaService;
	
	
	public ReservaController(UserService userService, ReservaService reservaService) {

		this.userService = userService;
		this.reservaService = reservaService;
	}
	
	//Lista todas las reservas habilitadas ordenadas por fecha
	@RequestMapping(value = "/reservas", method = RequestMethod.GET, produces = "application/json")
	public String reservas() {
		ArrayList<ReservaResponse> response=new ArrayList<ReservaResponse>();
		List<Reserva> reservas=reservaService.findReservasHabilitadas();
		for(Reserva r:reservas) {
			
			ReservaResponse rr=new ReservaResponse(r.getId(), r.getNumeroPersonas(), r.getNumeroReserva(),
					r.getEstadoR(), r.getUser().getEmail(),r.getCreatedAt());
			
			response.add(rr);
		}

		String json = new Gson().toJson(response);
		return json;
	}


	//nueva reserva para usuarios ya registrados (id)
	@RequestMapping(value = "/nuevaReserva", method = RequestMethod.POST, produces = "application/json")
	public String nuevaReserva ( @Valid @ModelAttribute ("reserva") Reserva reserva, BindingResult result,
			@RequestParam("usuarioId") Long usuarioId) {
		
		HashMap<String, String> myMap = new HashMap<String, String>();
		if(result.hasErrors()) {
			myMap.put("respuesta", "error");
			String json = new Gson().toJson(myMap);
			return json;
		}else {
			User user=userService.findUser(usuarioId);
			if(user==null) {
				myMap.put("respuesta", "usuario no encontrado");
				String json = new Gson().toJson(myMap);
				return json;
			}
			if(reservaService.findReservasActivas(user.getEmail()).size()!=0) {
				myMap.put("respuesta", "usuario ya tiene reserva activa");
				String json = new Gson().toJson(myMap);
				return json;
			}
			Random Num_Reserva = new Random();
			int minNumber = 10000;
			int Random = Num_Reserva.nextInt(minNumber) + 1;
			reserva.setNumeroReserva(Random);
			reserva.setEstadoR(true);
			reserva.setUser(user);
			reservaService.createNuevaReserva(reserva);
			
			ReservaResponse response=new ReservaResponse(reserva.getId(), reserva.getNumeroPersonas(), 
					reserva.getNumeroReserva(), reserva.getEstadoR(), reserva.getUser().getEmail(),reserva.getCreatedAt());
			String json = new Gson().toJson(response);
			return json;
		}   
	}

	//nueva reserva para usuarios ya registrados (email)
	@RequestMapping(value = "/reserva/crear/usuario", method = RequestMethod.POST, produces = "application/json")
	public String crearReserva(@Valid @ModelAttribute ("reserva") Reserva reserva, BindingResult result,
			@RequestParam("email") String email) {
		HashMap<String, String> myMap = new HashMap<String, String>();
		if(result.hasErrors()) {
			myMap.put("respuesta", "error");
			String json = new Gson().toJson(myMap);
			return json;
		}
		User user=userService.findByEmail(email);
		if(user==null) {
			myMap.put("respuesta", "usuario no encontrado");
			String json = new Gson().toJson(myMap);
			return json;
		}
		if(reservaService.findReservasActivas(email).size()!=0) {
			myMap.put("respuesta", "usuario ya tiene reserva activa");
			String json = new Gson().toJson(myMap);
			return json;
		}
		reserva.setUser(user);
		reserva.setEstadoR(true);
		Random Num_Orden = new Random();
		int minNumber = 100000;
		int random = Num_Orden.nextInt(minNumber) + 1;
		reserva.setNumeroReserva(random);
		reservaService.createNuevaReserva(reserva);
		
		ReservaResponse response=new ReservaResponse(reserva.getId(), reserva.getNumeroPersonas(), 
				reserva.getNumeroReserva(), reserva.getEstadoR(), reserva.getUser().getEmail(),reserva.getCreatedAt());
		String json = new Gson().toJson(response);
		return json;

	}



	@RequestMapping("/reserva/habilitar/{id}")
	public ReservaResponse activar (@PathVariable("id") Long id) {
		Reserva reserva=reservaService.findReserva(id);
		Boolean estadoR=true;
		reserva.setEstadoR(estadoR);
		reservaService.updateReserva(reserva);
		
		ReservaResponse response=new ReservaResponse(reserva.getId(), reserva.getNumeroPersonas(), 
				reserva.getNumeroReserva(), reserva.getEstadoR(), reserva.getUser().getEmail(),reserva.getCreatedAt());
		return  response;
	}

	@RequestMapping("/reserva/deshabilitar/{id}")
	public ReservaResponse deshabilitar (@PathVariable("id") Long id) {
		Reserva reserva=reservaService.findReserva(id);
		Boolean estadoR=false;
		reserva.setEstadoR(estadoR);
		reservaService.updateReserva(reserva);
		//enviar correo a tercero en fila
		ReservaResponse response=new ReservaResponse(reserva.getId(), reserva.getNumeroPersonas(), 
				reserva.getNumeroReserva(), reserva.getEstadoR(), reserva.getUser().getEmail(),reserva.getCreatedAt());
		return  response;
	}


}
