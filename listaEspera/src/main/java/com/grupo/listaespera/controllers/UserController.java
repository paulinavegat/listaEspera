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


//	@RequestMapping("/")
//	public String home(@ModelAttribute("user")User user) {
//		return "home.jsp";
//	}

	//entendemos que se crear el usuario para tener un numero de reserva
	//por ende, se le asigna inmediatamente un numero de reserva cuandos e le crea el perfil
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
			//reservaService.defaultEstadoR(reserva);
			reserva.setEstadoR(true);
			reserva.setUser(user);
			reservaService.createNuevaReserva(reserva);
			return "usuario creado y reserva creada";
		}   
	}
	//para usuarios ya registrados en el sistema, verificar con email para crear nueva reserva 

//	@RequestMapping("/login")
//	public String login(Principal principal, @ModelAttribute ("reserva") Reserva reserva) {
//
//		User currentUser = userService.findByEmail(principal.getName());
//		//Reserva currentReserva=reservaService.findReserva());
//
//		if ((currentUser.) {    // si el usario esta registrado por email y tiene reserva desactiva, se va a crear reserva
//			return "redirect:/nuevaReserva";
//		} else if ((currentUser.) {//si el usuario tiene reserva activa se va al home para esperar
//			return "redirect:/";
//					//"redirect:/nuevaReserva/" + currentUser.getId();
//		} else {
//			return "home.jsp"; 
//		}
//	}
	
	//incoporado por alvaro 14/8
    // @RequestMapping(value="/", method=RequestMethod.POST) 
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
