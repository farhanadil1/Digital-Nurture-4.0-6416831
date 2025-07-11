package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.model.Employee;
import com.example.EmployeeManagementSystem.projection.EmployeeNameEmail;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import com.example.EmployeeManagementSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id).orElse(null);
    }

    @PostMapping
    public Employee add(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
    @GetMapping("/search/name/{name}")
    public List<Employee> searchByName(@PathVariable String name) {
        return employeeService.getEmployeesByName(name);
    }

    @GetMapping("/search/department/{deptName}")
    public List<Employee> searchByDepartment(@PathVariable String deptName) {
        return employeeService.getEmployeesByDepartmentName(deptName);
    }
    
    @GetMapping("/search/email/{email}")
    public Employee searchByEmail(@PathVariable String email) {
        return employeeService.getEmployeeByEmail(email);
    }
    
    @GetMapping("/paginate")
    public Page<Employee> getPaginatedEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy) {
        return employeeService.getAllEmployeesPaginated(page, size, sortBy);
    }
    @GetMapping("/department/{deptId}/names")
    public List<EmployeeNameEmail> getEmployeeNames(@PathVariable Long deptId) {
        return employeeRepository.findByDepartmentId(deptId);
    }




}
