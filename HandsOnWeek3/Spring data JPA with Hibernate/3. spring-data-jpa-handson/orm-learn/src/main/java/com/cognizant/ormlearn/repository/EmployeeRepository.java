package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT AVG(e.salary) FROM Employee e")
    double getAverageSalary();

    @Query("SELECT AVG(e.salary) FROM Employee e WHERE e.department.id = :id")
    double getAverageSalaryByDepartment(@Param("id") int id);

    @Query("SELECT e FROM Employee e WHERE e.permanent = true")
    List<Employee> getAllPermanentEmployees();
    
   // Native query to get all employees
    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<Employee> getAllEmployeesNative();

}
