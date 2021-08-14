package com.grupo.listaEspera.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.grupo.listaEspera.models.Reserva;
import com.grupo.listaEspera.repositories.ReservaRepository;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<Reserva> allReserva() {
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
	public Reserva createOrUpdateReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
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