package com.SpringBoot.RestCrudApp.Service;

import java.util.List;

import com.SpringBoot.RestCrudApp.Entity.Employee;

public interface EmployeeService {
	
	List<Employee> findAll();

}
