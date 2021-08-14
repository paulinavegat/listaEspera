package com.grupo.listaEspera.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grupo.listaEspera.models.Reserva;

@Repository
public interface ReservaRepository extends CrudRepository<Reserva, Long>{
    List<Reserva> findAll();
}