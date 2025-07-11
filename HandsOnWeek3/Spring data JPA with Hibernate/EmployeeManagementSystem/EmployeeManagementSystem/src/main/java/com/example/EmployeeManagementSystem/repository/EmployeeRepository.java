package com.example.EmployeeManagementSystem.repository;

import com.example.EmployeeManagementSystem.model.Employee;
import com.example.EmployeeManagementSystem.projection.EmployeeNameEmail;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
    List<Employee> findByName(String name);

    // Custom HQL query using @Query
    @Query("SELECT e FROM Employee e WHERE e.department.name = :deptName")
    List<Employee> findEmployeesByDepartmentName(@Param("deptName") String deptName);
    
    @Query(name = "Employee.findByEmail")
    Employee findByEmail(@Param("email") String email);
    
    Page<Employee> findAll(Pageable pageable);
    
    List<EmployeeNameEmail> findByDepartmentId(Long departmentId);


}
