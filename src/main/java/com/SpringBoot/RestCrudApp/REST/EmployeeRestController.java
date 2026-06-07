package com.SpringBoot.RestCrudApp.REST;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.RestCrudApp.DAO.EmployeeDAO;
import com.SpringBoot.RestCrudApp.Entity.Employee;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	// define field for EmployeeDAO
	
	private EmployeeDAO employeeDao;
	
	// create constructor for dependency injection
	
	public EmployeeRestController(EmployeeDAO theEmployeeDao) {
		employeeDao = theEmployeeDao;
	}
	
	// create an endpoint for "/api/employees"
	
	@GetMapping("/employees")
	public List<Employee> findAll() {		
		return employeeDao.findAll();		
	}

}
