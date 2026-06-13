package com.SpringBoot.RestCrudApp.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.SpringBoot.RestCrudApp.Entity.Student;

// @RepositoryRestResource allows the endpoint exposed to be modified. /members instead of /students in this case.

@RepositoryRestResource(path = "members")
public interface StudentRepository  extends JpaRepository<Student, Integer>{

}
