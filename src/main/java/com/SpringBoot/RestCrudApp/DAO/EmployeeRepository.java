package com.SpringBoot.RestCrudApp.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBoot.RestCrudApp.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	// using JpaRepository instead of DAO with EntityManager. This gives us some methods by default.
	// no need to create an implementation either.

}
