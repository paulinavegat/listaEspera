package com.grupo.listaespera.controllers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
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
import com.grupo.listaespera.services.EmailService;
import com.grupo.listaespera.services.ReservaService;
import com.grupo.listaespera.services.UserService;
import com.grupo.listaespera.util.EmailSendGrid;
import com.grupo.listaespera.util.EmailSmtpGmail;
import com.grupo.listaespera.util.ReservaResponse;

@RestController
public class ReservaController {
	private UserService userService;
	private ReservaService reservaService;
	private EmailService emailService;
	
	public ReservaController(UserService userService, ReservaService reservaService,EmailService emailService) {
		this.emailService = emailService;
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


//	//nueva reserva para usuarios ya registrados (id)
//	@RequestMapping(value = "/nuevaReserva", method = RequestMethod.POST, produces = "application/json")
//	public String nuevaReserva ( @Valid @ModelAttribute ("reserva") Reserva reserva, BindingResult result,
//			@RequestParam("usuarioId") Long usuarioId) {
//		
//		HashMap<String, String> myMap = new HashMap<String, String>();
//		if(result.hasErrors()) {
//			myMap.put("respuesta", "error");
//			String json = new Gson().toJson(myMap);
//			return json;
//		}else {
//			User user=userService.findUser(usuarioId);
//			if(user==null) {
//				myMap.put("respuesta", "usuario no encontrado");
//				String json = new Gson().toJson(myMap);
//				return json;
//			}
//			if(reservaService.findReservasActivas(user.getEmail()).size()!=0) {
//				myMap.put("respuesta", "usuario ya tiene reserva activa");
//				String json = new Gson().toJson(myMap);
//				return json;
//			}
//			Random Num_Reserva = new Random();
//			int minNumber = 10000;
//			int Random = Num_Reserva.nextInt(minNumber) + 1;
//			reserva.setNumeroReserva(Random);
//			reserva.setEstadoR(true);
//			reserva.setUser(user);
//			reservaService.createNuevaReserva(reserva);
//			
//			ReservaResponse response=new ReservaResponse(reserva.getId(), reserva.getNumeroPersonas(), 
//					reserva.getNumeroReserva(), reserva.getEstadoR(), reserva.getUser().getEmail(),reserva.getCreatedAt());
//			String json = new Gson().toJson(response);
//			return json;
//		}   
//	}
//
//	//nueva reserva para usuarios ya registrados (email)
//	@RequestMapping(value = "/reserva/crear/usuario", method = RequestMethod.POST, produces = "application/json")
//	public String crearReserva(@Valid @ModelAttribute ("reserva") Reserva reserva, BindingResult result,
//			@RequestParam("email") String email) {
//		HashMap<String, String> myMap = new HashMap<String, String>();
//		if(result.hasErrors()) {
//			myMap.put("respuesta", "error");
//			String json = new Gson().toJson(myMap);
//			return json;
//		}
//		User user=userService.findByEmail(email);
//		if(user==null) {
//			myMap.put("respuesta", "usuario no encontrado");
//			String json = new Gson().toJson(myMap);
//			return json;
//		}
//		if(reservaService.findReservasActivas(email).size()!=0) {
//			myMap.put("respuesta", "usuario ya tiene reserva activa");
//			String json = new Gson().toJson(myMap);
//			return json;
//		}
//		reserva.setUser(user);
//		reserva.setEstadoR(true);
//		Random Num_Orden = new Random();
//		int minNumber = 100000;
//		int random = Num_Orden.nextInt(minNumber) + 1;
//		reserva.setNumeroReserva(random);
//		reservaService.createNuevaReserva(reserva);
//		
//		ReservaResponse response=new ReservaResponse(reserva.getId(), reserva.getNumeroPersonas(), 
//				reserva.getNumeroReserva(), reserva.getEstadoR(), reserva.getUser().getEmail(),reserva.getCreatedAt());
//		String json = new Gson().toJson(response);
//		return json;
//
//	}


//
//	@RequestMapping("/reserva/habilitar/{id}")
//	public ReservaResponse activar (@PathVariable("id") Long id) {
//		Reserva reserva=reservaService.findReserva(id);
//		Boolean estadoR=true;
//		reserva.setEstadoR(estadoR);
//		reservaService.updateReserva(reserva);
//		
//		ReservaResponse response=new ReservaResponse(reserva.getId(), reserva.getNumeroPersonas(), 
//				reserva.getNumeroReserva(), reserva.getEstadoR(), reserva.getUser().getEmail(),reserva.getCreatedAt());
//		return  response;
//	}
//
	
	@RequestMapping("/reserva/deshabilitar/{id}")
	public ReservaResponse deshabilitar (@PathVariable("id") Long id) throws IOException {
		
		Reserva reserva=reservaService.findReserva(id);
		if(reserva==null) {
			return null;
		}
		if(!reserva.getEstadoR()) {
			return null;
		}
		Boolean estadoR=false;
		reserva.setEstadoR(estadoR);
		reservaService.updateReserva(reserva);
		
		List<Reserva> reservas=reservaService.findReservasHabilitadas();

		int j;
		if(reservas.size()<=5) {
			j=reservas.size();
		}else {
			j=5;
		}
		
		for(int i=0;i<j;i++) {
			Reserva res=reservas.get(i);
			Integer posicion=i+1;
	
			//EmailSmtpGmail.enviarNotificacion(res.getUser().getEmail(), posicion,res.getId());
			//EmailSendGrid.sendEmail(res.getUser().getEmail(),posicion,res.getId());
			System.out.println(res.getUser().getEmail()+" Posicion: "+ posicion+" Reserva id: "+res.getId());

//			try {
//				emailService.sendEmail(res.getUser().getEmail(), posicion,res.getId());
//			} catch (MessagingException e) {
//				// TODO Auto-generated catch block
//				System.out.println("error al enviar correo");
//				e.printStackTrace();
//			}
		}
		
		ReservaResponse response=new ReservaResponse(reserva.getId(), reserva.getNumeroPersonas(), 
				reserva.getNumeroReserva(), reserva.getEstadoR(), reserva.getUser().getEmail(),reserva.getCreatedAt());
		return  response;
	}

	@RequestMapping("/reserva/cambiarstatus/{idReserva}")
	public ReservaResponse cambiarStatus(@PathVariable("idReserva") Long idReserva) throws IOException {
		
		Reserva reserva=reservaService.findReserva(idReserva);
		if(reserva==null) {
			return null;
		}
		reserva.setEstadoR(!reserva.getEstadoR());
		reserva=reservaService.updateReserva(reserva);
		
		List<Reserva> reservas=reservaService.findReservasHabilitadas();
		
		int j;
		if(reservas.size()<=5) {
			j=reservas.size();
		}else {
			j=5;
		}
		
		for(int i=0;i<j;i++) {
			Reserva res=reservas.get(i);
			Integer posicion=i+1;
	
			//EmailSmtpGmail.enviarNotificacion(res.getUser().getEmail(), posicion,res.getId());
			//EmailSendGrid.sendEmail(res.getUser().getEmail(),posicion,res.getId());
			System.out.println(res.getUser().getEmail()+" Posicion: "+ posicion+" Reserva id: "+res.getId());
			
		}
		
		
		ReservaResponse response=new ReservaResponse(reserva.getId(), reserva.getNumeroPersonas(), 
				reserva.getNumeroReserva(), reserva.getEstadoR(), reserva.getUser().getEmail(),reserva.getCreatedAt());
		return  response;
	}

}
