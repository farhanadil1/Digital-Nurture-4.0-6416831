package com.example.rest.dao;

import com.example.rest.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDao {
    public static List<Department> DEPARTMENT_LIST;

    @Autowired
    public DepartmentDao(@Qualifier("departmentList") List<Department> list) {
        DEPARTMENT_LIST = list;
    }

    public List<Department> getAllDepartments() {
        return DEPARTMENT_LIST;
    }
}
