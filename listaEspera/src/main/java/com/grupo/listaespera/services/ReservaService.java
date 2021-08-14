package com.grupo.listaespera.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.grupo.listaespera.models.Reserva;
import com.grupo.listaespera.repositories.ReservaRepository;

@Service
public class ReservaService {
	
	ReservaRepository reservaRepository;
	public ReservaService(ReservaRepository reservaRepository){
        this.reservaRepository = reservaRepository;
    }

	public List<Reserva> allReservas() {
		return reservaRepository.findAll();
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


