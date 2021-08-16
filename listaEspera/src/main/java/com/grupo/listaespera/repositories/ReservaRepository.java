package com.grupo.listaespera.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grupo.listaespera.models.Reserva;

@Repository
public interface ReservaRepository extends CrudRepository<Reserva, Long> {
	 List<Reserva> findAll();

	 @Query("SELECT r FROM Reserva r WHERE r.estadoR=true ORDER BY r.createdAt ASC")
	 List<Reserva> findReservasHabilitadas();
	 
	 @Query("SELECT r FROM Reserva r WHERE r.estadoR=true AND r.user.email=?1")
	 List<Reserva> findReservasActivas(String email);
}

