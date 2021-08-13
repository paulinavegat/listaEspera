package com.grupo.listaEspera.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.grupo.listaEspera.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	   List<User> findAll();
	    User findByEmail(String email);
	    
	    
}
