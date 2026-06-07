package com.SpringBoot.RestCrudApp.REST;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	// expose an endpoint for POST "/employees" to add a new employee. @RequestBody tells the controller that the HTTP
	// request body should be bound to the method parameter which is theEmployee here
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		
		// set the employee ID to 0 in case the user has included one
		theEmployee.setId(0);
		
		// save the employee
		Employee dbEmployee = employeeService.save(theEmployee);
		
		// return the saved employee
		return dbEmployee;
	}
	
}
