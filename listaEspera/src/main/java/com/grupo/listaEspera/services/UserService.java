package com.grupo.listaEspera.services;

import java.util.List;
import java.util.Optional;

import com.grupo.listaEspera.models.User;
import com.grupo.listaEspera.repositories.UserRepository;

public class UserService {
	
	private UserRepository userRepository;
	
	public List<User> allUser() {
        return userRepository.findAll();
    }
    public User findUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }
	public User createUser(User user) {
        return userRepository.save(user);
	}
    
	 public User updateUser(User user) {
	    return userRepository.save(user);
	  }
        
    
	public void deleteUser(Long id) {
    	Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
        	userRepository.deleteById(id);
        } else {
            return;
        }
    }
	
}

