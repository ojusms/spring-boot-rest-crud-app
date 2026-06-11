package com.SpringBoot.RestCrudApp.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBoot.RestCrudApp.Entity.Student;

public interface StudentRepository  extends JpaRepository<Student, Integer>{

}
