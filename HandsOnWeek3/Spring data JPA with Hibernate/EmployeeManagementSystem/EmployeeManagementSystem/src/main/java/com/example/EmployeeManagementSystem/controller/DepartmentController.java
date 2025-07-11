package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.model.Department;
import com.example.EmployeeManagementSystem.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public List<Department> getAll() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id).orElse(null);
    }

    @PostMapping
    public Department add(@RequestBody Department department) {
        return departmentService.addDepartment(department);
    }

    @PutMapping("/{id}")
    public Department update(@PathVariable Long id, @RequestBody Department department) {
        return departmentService.updateDepartment(id, department);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }
}
