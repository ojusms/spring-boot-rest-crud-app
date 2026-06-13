package com.SpringBoot.RestCrudApp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SpringBoot.RestCrudApp.DAO.EmployeeRepository;
import com.SpringBoot.RestCrudApp.Entity.Employee;

// Service layer implements Service Facade design pattern. The service just delegates to DAO function calls underneath here.
// This is considered a best practice. This helps in case there are multiple DAO objects for the same entity.

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	// updating the EmployeeService implementation to use the new JpaRepository instead of EntityManager DAO.
	// no changes needed in the EmployeeRestController.
	
	// define field for EmployeeRepository which extends JpaRepository
	
	private EmployeeRepository employeeRepository;
	
	// define constructor for dependency injection. @Autowired is not needed since EmployeeDAO is marked with @repository.
	// Spring knows to inject it since there is only one constructor.
	
	public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
		employeeRepository = theEmployeeRepository; 
	}

	@Override
	public List<Employee> findAll() {		
		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(int theId) {
		
		// update method to use Optional (Java 8 feature)
		
		Optional<Employee> tempEmployee = employeeRepository.findById(theId);
		
		Employee dbEmployee = null;
		
		// throw exception if null
		if (tempEmployee == null ) {
			throw new RuntimeException("Employee not found with ID " + theId);
		}
		
		// return employee if not null
		else {
			dbEmployee = tempEmployee.get();
		}
		
		return dbEmployee;
	}

	// adding @Transactional annotation here since the Service layer manages transaction boundaries
	
	@Override
	@Transactional
	public Employee save(Employee theEmployee) {
		return employeeRepository.save(theEmployee);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
		
	}

/*	Showing EmployeeDAO implementation with Entity Manager for show casing purposes 
	
	private EmployeeDAO employeeDAO;

	public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO) {
		employeeDAO = theEmployeeDAO; 
	}

	@Override
	public List<Employee> findAll() {		
		return employeeDAO.findAll();
	}

	@Override
	public Employee findById(int theId) {
		return employeeDAO.findById(theId);
	}

	// adding @Transactional annotation here since the Service layer manages transaction boundaries
	
	@Override
	@Transactional
	public Employee save(Employee theEmployee) {
		return employeeDAO.save(theEmployee);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		employeeDAO.deleteById(theId);
		
	}
*/
}
