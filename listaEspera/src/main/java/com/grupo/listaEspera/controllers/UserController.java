package com.grupo.listaEspera.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.grupo.listaEspera.models.User;
import com.grupo.listaEspera.services.UserService;

@Controller
public class UserController {
	private UserService userService;
	private ReservaService reservaService;
	
	
	public UserController(UserService userService, ReservaService reservaService) {
	
		this.userService = userService;
		this.reservaService = reservaService;
	}
	

		@RequestMapping("/")
	    public String newUser(@ModelAttribute("user")User user) {
		return "/user/nuevo.jsp";
	    }
	    
	    // @RequestMapping(value="/", method=RequestMethod.POST) 
	    @PostMapping("/users")
	    public String createUser(@Valid @ModelAttribute ("user")User user, BindingResult result) {
	     if(result.hasErrors()) {
			return "/user/nuevo.jsp";
		}else {
		    userService.createUser(user);
		    return "redirect:/";
		}   
	  }
}
