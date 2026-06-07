package com.SpringBoot.RestCrudApp.DAO;

import java.util.List;

import com.SpringBoot.RestCrudApp.Entity.Employee;

public interface EmployeeDAO {
	
	public List<Employee> findAll();

}
