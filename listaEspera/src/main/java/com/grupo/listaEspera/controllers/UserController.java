package com.grupo.listaEspera.controllers;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo.listaEspera.models.User;
import com.grupo.listaEspera.services.ReservaService;
import com.grupo.listaEspera.services.UserService;

@RestController
public class UserController {
	private UserService userService;
	private ReservaService reservaService;
	
	
	public UserController(UserService userService, ReservaService reservaService) {
	
		this.userService = userService;
		this.reservaService = reservaService;
	}
	    
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
