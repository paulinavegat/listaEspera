package com.grupo.listaespera.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grupo.listaespera.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	   List<User> findAll();
	    User findByEmail(String email);
	    
	    
}
