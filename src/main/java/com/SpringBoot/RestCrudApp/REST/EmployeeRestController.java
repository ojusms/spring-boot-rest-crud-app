package com.SpringBoot.RestCrudApp.REST;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
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

}
