package com.grupo.listaespera.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grupo.listaespera.models.Reserva;

@Repository
public interface ReservaRepository extends CrudRepository<Reserva, Long> {
	 List<Reserva> findAll();

	//Reserva findById(Long id);
	    
}

