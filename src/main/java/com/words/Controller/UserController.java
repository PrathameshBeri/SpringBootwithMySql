package com.words.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.words.entity.User;
import com.words.repository.UserRepository;

import javassist.tools.web.BadHttpRequest;

@RestController
@RequestMapping(path = "/users")
public class UserController {

	
	@Autowired
	public UserRepository userRepo;
	
	@GetMapping
	public Iterable<User> findAll(){
		return userRepo.findAll();
	}
	
	
	@GetMapping(value = "/{username}")
	public Optional<User> findUsername(@PathVariable("username") String username) {
		
		return userRepo.findById(username);
	
	}
	
	@PostMapping(consumes = "application/json")
	public User createUser(@RequestBody User user) {
		return userRepo.save(user);
	}
	
	@DeleteMapping(path = "/{username}")
	public void delUser(@PathVariable("username") String username) {
		
		userRepo.deleteById(username);
	}
	
	@PutMapping(value = "/{username}")
	public void updateUser(@PathVariable("username") String username, @RequestBody User user ) throws BadHttpRequest {
		
		if(userRepo.existsById(username)) {
			user.setUsername(username);
			userRepo.save(user);
		}
		else { throw new BadHttpRequest(); }
	}
	
	
}
