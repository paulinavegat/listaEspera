package com.grupo.listaespera.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
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
	
	@GetMapping("/")
    public String helloWorld() {
        return "No necesitas loguearte de nuevo";
    }

    @GetMapping("/not-restricted")
    public String notRestricted() {
        return "No necesitas loguearte de nuevo";
    }

    @GetMapping("/restricted")
    public String restricted() {
        return "Necesitas ingresar a tu cuenta ";
    }

	//crea nuevo usuario y crea nueva reserva
	@RequestMapping(value = "/registro", method = RequestMethod.POST, produces = "application/json")
	public String crearUser(@Valid @ModelAttribute ("user")User user, @Valid @ModelAttribute ("reserva") Reserva reserva, 
			BindingResult result) {
		if(result.hasErrors()) {
			HashMap<String, String> myMap = new HashMap<String, String>();
			myMap.put("respuesta", "error");
			String json = new Gson().toJson(myMap);
			return json;
		}else {
			User usuario =userService.findByEmail(user.getEmail());
			if(usuario!=null) {
				List<Reserva> reservas=reservaService.findReservasActivas(usuario.getEmail());
				if(reservas.size()>0) {
					HashMap<String, String> myMap = new HashMap<String, String>();
					myMap.put("respuesta", "Usuario tiene reserva activa");
					String json = new Gson().toJson(myMap);
					return json;
				}else {
					//crea reserva para usuario existente
					Random Num_Reserva = new Random();
					int minNumber = 10000;
					int Random = Num_Reserva.nextInt(minNumber) + 1;
					reserva.setNumeroReserva(Random);
					reserva.setEstadoR(true);
					reserva.setUser(usuario);
					Reserva reserva2=reservaService.createNuevaReserva(reserva);
					
					HashMap<String, String> myMap = new HashMap<String, String>();
					myMap.put("Reserva id", String.valueOf(reserva2.getId()));
					String json = new Gson().toJson(myMap);
					return json;
				}
				
			}else {
				//crea usuario y reserva
				userService.createUser(user);
				Random Num_Reserva = new Random();
				int minNumber = 10000;
				int Random = Num_Reserva.nextInt(minNumber) + 1;
				reserva.setNumeroReserva(Random);
				reserva.setEstadoR(true);
				reserva.setUser(user);
				reservaService.createNuevaReserva(reserva);
			}
			
			
			String json = new Gson().toJson(reserva);
			return json;
		}


	}

//	//crea un usuario sin crear reserva
//	@RequestMapping(value = "/user/crear", method = RequestMethod.POST, produces = "application/json")
//	public String createUser(@Valid @ModelAttribute ("user")User user, BindingResult result) {
//		if(result.hasErrors()) {
//			HashMap<String, String> myMap = new HashMap<String, String>();
//			myMap.put("respuesta", "error");
//			String json = new Gson().toJson(myMap);
//			return json;
//		}else {
//			if(userService.findByEmail(user.getEmail())!=null) {
//				HashMap<String, String> myMap = new HashMap<String, String>();
//				myMap.put("respuesta", "Email ya existe, no se ha creado usuario");
//				String json = new Gson().toJson(myMap);
//				return json;
//			}
//			user=userService.createUser(user);
//			String json = new Gson().toJson(user);
//			return json;
//		}   
//	}
}

