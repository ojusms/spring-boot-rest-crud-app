package com.SpringBoot.RestCrudApp.REST;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.RestCrudApp.Entity.Employee;
import com.SpringBoot.RestCrudApp.Service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	// define field for EmployeeService
	
	private EmployeeService employeeService;
	
	// create constructor for dependency injection. 
	
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	// create an endpoint for "/api/employees"
	
	@GetMapping("/employees")
	public List<Employee> findAll() {		
		return employeeService.findAll();		
	}
	
	// expose an endpoint for GET "/api/employees/{employeeId}"
	
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployeeById(@PathVariable int employeeId) {
		
		// find employee
		Employee theEmployee = employeeService.findById(employeeId);
		
		if (theEmployee == null)
			throw new RuntimeException("Employee ID not found - " + employeeId);
		
		// return employee
		return theEmployee;
		
	}

}
