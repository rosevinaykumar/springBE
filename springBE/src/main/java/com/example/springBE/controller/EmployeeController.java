package com.example.springBE.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springBE.model.Employee;
import com.example.springBE.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@PostMapping("/employee/add")
	public String addEmployee(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email) {
		Employee emp = new Employee(firstName, lastName, email);
		/*Employee emp =  new Employee();
		emp.setFirstName(firstName);
		emp.setLastName(lastName);
		emp.setEmail(email);*/
		employeeRepo.save(emp);
		return "saved";
	}
	@GetMapping("/employees")
	public List<Employee> getAllEmployee() {
		return employeeRepo.findAll();
	}
}
