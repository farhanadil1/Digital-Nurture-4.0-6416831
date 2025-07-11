package com.example.EmployeeManagementSystem.model;

import org.hibernate.annotations.BatchSize;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employees")
@NamedQueries({
    @NamedQuery(
        name = "Employee.findByEmail",
        query = "SELECT e FROM Employee e WHERE e.email = :email"
    )
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@BatchSize(size = 10)
public class Employee extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;


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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", department=" + department + "]";
	}
    
    
}
