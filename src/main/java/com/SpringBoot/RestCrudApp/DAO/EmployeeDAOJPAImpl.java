package com.SpringBoot.RestCrudApp.DAO;

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

	@Override
	public Employee findById(int theId) {
		// find employee
		Employee theEmployee = entityManager.find(Employee.class, theId);
		
		// return employee
		return theEmployee;
	}

	// @Transactional annotation is not used here but instead in the Service layer.
	@Override
	public Employee save(Employee theEmployee) {
		// save employee. merge() creates a new employee record if ID = 0 or updates an existing one if not.
		Employee savedEmployee = entityManager.merge(theEmployee);
		
		// return saved employee with updated ID
		return savedEmployee;
	}

	// same as previous, @Transactional annotation not used here in the DAO, used in the Service layer.
	@Override
	public void deleteById(int theId) {
		// find employee
		Employee dbEmployee = entityManager.find(Employee.class, theId);
		
		// delete employee
		entityManager.remove(dbEmployee);
		
		
	}

}
