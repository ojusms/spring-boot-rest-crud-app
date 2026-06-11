package com.SpringBoot.RestCrudApp.REST;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.RestCrudApp.Entity.Employee;
import com.SpringBoot.RestCrudApp.Service.EmployeeService;

import tools.jackson.databind.json.JsonMapper;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	// define field for EmployeeService
	// adding a field for JsonMapper for PATCH endpoint for partial update of employee record
	
	private EmployeeService employeeService;
	
	private JsonMapper jsonMapper;
	
	// create constructor for dependency injection. 
	// update constructor to include JsonMapper for Dependency Injection
	
	public EmployeeRestController(EmployeeService theEmployeeService, JsonMapper theJsonMapper) {
		employeeService = theEmployeeService;
		jsonMapper = theJsonMapper;
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
	
	// expose an endpoint for PUT "/employees" to update the existing employee data
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		
		// update the employee and return the updated employee obj
		Employee dbEmployee = employeeService.save(theEmployee);
		
		return dbEmployee;
	}
	
	// expose an endpoint for PATCH "employees/{employeeId}" to do a partial update on employee record
	
	@PatchMapping("/employees/{employeeId}")
	public Employee partialUpdate(@PathVariable int employeeId, @RequestBody Map<String, Object> patchPayload) {
		
		// retrieve employee to be patched
		
		Employee tempEmployee = employeeService.findById(employeeId);
		
		// throw exception if no such employee
		
		if (tempEmployee == null) {
			throw new RuntimeException("Employee not found - " + employeeId);
		}
		
		// throw an exception if employeeId is part of request body, we do not allow primary key to be updated
		
		if (patchPayload.containsKey("id")) {
			throw new RuntimeException("Request body cannot contain ID");
		}
		
		Employee patchedEmployee = jsonMapper.updateValue(tempEmployee, patchPayload);
		
		Employee dbEmployee = employeeService.save(patchedEmployee);
		
		return dbEmployee;
	}
	
	// expose an endpoint for DELETE "employees/{employeeId}" to delete an employee
	
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		
		// retrieve employee by ID
		Employee tempEmployee = employeeService.findById(employeeId);
		
		// throw exception if not found
		if (tempEmployee == null) {
			throw new RuntimeException("Employee not found with ID " + employeeId);
		}
		
		//delete employee
		employeeService.deleteById(employeeId);
		
		return "Deleted employee with ID " + employeeId;
	}
}
