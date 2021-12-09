package com.example.springBE.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.springBE.exception.ResourceNotFoundException;
import com.example.springBE.model.Employee;
import com.example.springBE.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins="http://localhost:4200")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	// Create New Employee
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		//Employee emp = new Employee(firstName, lastName, email);
		/*Employee emp =  new Employee();
		emp.setFirstName(firstName);
		emp.setLastName(lastName);
		emp.setEmail(email);*/
		return employeeRepo.save(employee);
	}
	
	// Get the list of employees
//	@GetMapping("/employees")
	@RequestMapping(method=RequestMethod.GET, value="/employees")
	@CrossOrigin(origins="http://localhost:4200")
	public List<Employee> getAllEmployee() {
		return employeeRepo.findAll();
	}
	
	/*@PutMapping("/employees/{id}")
	public Employee updateEmployee(@PathVariable("id") Long id) {
		return employeeRepo.
	}
	*/
	
	//Get the employee with the id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee>  getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("The employee record is not found by"+ id));
		return ResponseEntity.ok(employee);
	}
	
	//Update the changes the employee with REST API
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long id, @RequestBody Employee employeeDetails) {
		
		Employee employee = employeeRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("The employee record is not found by"+id));
		
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmail(employeeDetails.getEmail());
		
		Employee updatedEmployee = employeeRepo.save(employee);
		
		return ResponseEntity.ok(updatedEmployee);
	}
	
	@DeleteMapping("/employees/{id}")
	public void deleteEmployeeById(@PathVariable Long id){
		Employee employee = employeeRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("not Found"));
		 employeeRepo.delete(employee);
		
	}
}
