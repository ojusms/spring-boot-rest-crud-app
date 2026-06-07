package com.SpringBoot.RestCrudApp.DAO;

import java.lang.invoke.TypeDescriptor;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.SpringBoot.RestCrudApp.Entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDAOJPAImpl implements EmployeeDAO {
	
	// define field for entity manager
	
	private EntityManager entityManager;
	
	// create constructor for dependency injection
	
	@Autowired
	public EmployeeDAOJPAImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<Employee> findAll() {
		
		// create query
		
		TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);
		
		// execute query and store result
		
		List<Employee> employees = theQuery.getResultList();
		
		// return result
		
		return employees;
	}

}
