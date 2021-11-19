package com.example.springBE;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping(path="/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
	@PostMapping(path="/add")
	public @ResponseBody String adduser(@RequestParam String name, @RequestParam String email) {
		
		User u = new User();
		u.setName(name);
		u.setEmail(email);
		userRepo.save(u);
		
		return "Saved";
		
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> all() {
	
		return userRepo.findAll();
	}
	
	
}
