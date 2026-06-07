package com.SpringBoot.RestCrudApp.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SpringBoot.RestCrudApp.DAO.EmployeeDAO;
import com.SpringBoot.RestCrudApp.Entity.Employee;

// Service layer implements Service Facade design pattern. The service just delegates to DAO function calls underneath here.
// This is considered a best practice. This helps in case there are multiple DAO objects for the same entity.

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	// define filed for employeeDAO
	
	private EmployeeDAO employeeDAO;
	
	// define constructor for dependency injection. @autowired is not needed since EmployeeDAO is marked with @repository.
	// Spring knows to inject it since there is only one constructor.
	
	public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO) {
		employeeDAO = theEmployeeDAO; 
	}

	@Override
	public List<Employee> findAll() {		
		return employeeDAO.findAll();
	}

}
