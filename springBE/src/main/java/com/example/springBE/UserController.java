package com.example.springBE;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
	@PostMapping(path="/add")
	public String adduser(@RequestParam String name, @RequestParam String email) {
		
		User u = new User();
		u.setName(name);
		u.setEmail(email);
		userRepo.save(u);
		
		return "Saved";
		
	}
	
	@GetMapping(path="/all")
	public Iterable<User> all() {
	
		return userRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<User> getUser(@PathVariable Integer id) {
		return userRepo.findById(id);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable("id") Integer id) {
		userRepo.deleteById(id);
	}
	
}
