package com.SpringBoot.RestCrudApp.DAO;

import java.util.List;

import com.SpringBoot.RestCrudApp.Entity.Employee;

public interface EmployeeDAO {
	
	List<Employee> findAll();
	
	Employee findById(int theId);
	
	Employee save(Employee theEmployee);
	
	void deleteById(int theId);

}
