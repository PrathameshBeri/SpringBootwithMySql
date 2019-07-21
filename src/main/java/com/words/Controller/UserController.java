package com.words.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity findAll(){
		return ResponseEntity.ok(userRepo.findAll());
	}
	
	
	@GetMapping(value = "/{username}")
	public ResponseEntity findUsername(@PathVariable("username") String username) {
		
		return ResponseEntity.ok(userRepo.findById(username));
	
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity createUser(@RequestBody User user) {
		return ResponseEntity.ok(userRepo.save(user));
	}
	
	@DeleteMapping(path = "/{username}")
	public ResponseEntity delUser(@PathVariable("username") String username) {
		
		User user = userRepo.findById(username).get();
		userRepo.deleteById(username);
		return ResponseEntity.ok(user);
		
	}
	
	@PutMapping(value = "/{username}")
	public ResponseEntity updateUser(@PathVariable("username") String username, @RequestBody User user ) throws BadHttpRequest {
		
		if(userRepo.existsById(username)) {
			user.setUsername(username);
			User user1 = userRepo.save(user);
			return ResponseEntity.ok(user1);
		}
		else { throw new BadHttpRequest(); }
	}
	
	
}
