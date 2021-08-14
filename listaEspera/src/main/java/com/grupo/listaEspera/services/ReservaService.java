package com.grupo.listaEspera.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.grupo.listaEspera.models.Reserva;
import com.grupo.listaEspera.repositories.ReservaRepository;

@Service
public class ReservaService {
	
	ReservaRepository reservaRepository;
	

	public List<Reserva> allReservas() {
		return reservaRepository.findAll();
	}

	public Reserva findById(Long id) {
		return reservaRepository.finById();
	}
	
    public Reserva findReserva(Long id) {
        Optional<Reserva> optionalReserva = reservaRepository.findById(id);
        if(optionalReserva.isPresent()) {
            return optionalReserva.get();
        } else {
            return null;
        }
    }
	public Reserva createNuevaReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
	}
    
	 public Reserva updateReserva(Reserva reserva) {
	    return reservaRepository.save(reserva);
	  }
	 
	 public void defaultEstadoR (Reserva reserva) {
			reserva.setEstadoR(true);
			reservaRepository.save(reserva);
		}
        
    
	public void deleteReserva(Long id) {
    	Optional<Reserva> optionalReserva = reservaRepository.findById(id);
        if(optionalReserva.isPresent()) {
        	reservaRepository.deleteById(id);
        } else {
            return;
        }
    }
	
}


