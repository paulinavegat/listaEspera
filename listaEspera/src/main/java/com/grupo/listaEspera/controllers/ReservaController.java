package com.grupo.listaEspera.controllers;
import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.grupo.listaEspera.models.Reserva;
import com.grupo.listaEspera.services.ReservaService;
import com.grupo.listaEspera.services.UserService;

@Controller
public class ReservaController {
	private UserService userService;
	private ReservaService reservaService;
	
	
	public ReservaController(UserService userService, ReservaService reservaService) {
	
		this.userService = userService;
		this.reservaService = reservaService;
	}
	
	  @RequestMapping("/reservas")
	    public String reservas(Model model) {
	        List<Reserva> reservas = reservaService.allReservas();
	        model.addAttribute("reservas", reservas);
	        return "home.jsp";
	    }
	
	
	//nueva reserva para usuarios ya registrados
	   @PostMapping("/nuevaReserva")
	   public String nuevaReserva ( @Valid @ModelAttribute ("reserva") Reserva reserva, BindingResult result) {
		     if(result.hasErrors()) {
		    	 	return "home.jsp";
				}else {
				    reservaService.createNuevaReserva(reserva);
				    Random Num_Reserva = new Random();
			    	int minNumber = 10000;
					int Random = Num_Reserva.nextInt(minNumber) + 1;
			        reserva.setNumeroReserva(Random);
				    reservaService.defaultEstadoR(reserva);
				    return "redirect:/";
				}   
			  }
	   //incorporado por alvaro 14/8
//		//metodo temporal de prueba
//		@RequestMapping(value="/reserva/crear/usuario/{email}",method=RequestMethod.POST)
//		public String crearReserva(@Valid @ModelAttribute ("reserva") Reserva reserva, BindingResult result,
//				@PathVariable("email") String email) {
//			
//			if(result.hasErrors()) {
//				return "error creando reserva";
//			}
//			User user=userService.findByEmail(email);
//			reserva.setUser(user);
//			reserva.setEstado(true);
//			Random Num_Orden = new Random();
//	    	int minNumber = 100000;
//			int random = Num_Orden.nextInt(minNumber) + 1;
//			reserva.setNumeroReserva((long) random);
//			reservaService.createOrUpdateReserva(reserva);
//			return "reserva creada";
//			 
//		}
//	   
	   
	   //seguro hay un metodo para hablitar y deshabilitar pero yo no s[e hacerlo juntos 

	   @RequestMapping("/reserva/habilitar/{id}")
	   public String activar (@PathVariable("id") Long id) {
		   Reserva reserva=reservaService.findById(id);
		   Boolean estadoR=true;
		   reserva.setEstadoR(estadoR);
		   reservaService.updateReserva(reserva);
		   return  "redirect:/reservas";
	   }

	   @RequestMapping("/reserva/deshabilitar/{id}")
	   public String deshabilitar (@PathVariable("id") Long id) {
		   Reserva reserva=reservaService.findById(id);
		   Boolean estadoR=false;
		   reserva.setEstadoR(estadoR);
		   reservaService.updateReserva(reserva);
		   return  "redirect:/reservas";
	   }


}
