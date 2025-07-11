package com.example.EmployeeManagementSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert  
@DynamicUpdate  
@Table(name = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor 
public class Department extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // One to Many Relationship with Employee
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employees;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}
    
    
}
