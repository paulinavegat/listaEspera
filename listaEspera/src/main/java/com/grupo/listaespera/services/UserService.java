package com.grupo.listaespera.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.grupo.listaespera.models.User;
import com.grupo.listaespera.repositories.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
	
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
    
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
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

