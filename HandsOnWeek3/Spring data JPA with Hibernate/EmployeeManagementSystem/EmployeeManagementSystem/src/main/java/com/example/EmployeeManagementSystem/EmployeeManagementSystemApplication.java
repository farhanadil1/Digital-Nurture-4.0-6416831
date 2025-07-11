package com.example.EmployeeManagementSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.EmployeeManagementSystem.model.Department;
import com.example.EmployeeManagementSystem.model.Employee;
import com.example.EmployeeManagementSystem.repository.DepartmentRepository;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaAuditing
public class EmployeeManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSystemApplication.class, args);
	}
	//@Autowired
	//private EmployeeRepository employeeRepository;

	//@Autowired
	//private DepartmentRepository departmentRepository;

	//@PostConstruct
	//public void testExercise2_CreateEntities() {
//	    Department dept = new Department();
//	    dept.setName("IT");
//	    departmentRepository.save(dept);
//
//	    Employee emp = new Employee();
//	    emp.setName("Adil Farhan");
//	    emp.setEmail("adil@example.com");
//	    emp.setDepartment(dept);
//	    employeeRepository.save(emp);
//
//	    System.out.println("Exercise 2 Output:");
//	    System.out.println("Saved Employee: " + emp);
//	    System.out.println("Saved Department: " + dept);
//	}


}
