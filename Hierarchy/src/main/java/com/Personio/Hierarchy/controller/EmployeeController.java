package com.Personio.Hierarchy.controller;

import com.Personio.Hierarchy.model.Employee;
import com.Personio.Hierarchy.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl hierarchyService;

    @PostMapping
    public void updateEmployees(@RequestBody Map<String, String> employeeMap) {

        hierarchyService.updateEmployees(employeeMap);
    }

    @GetMapping("/id")
    public void getEmployeeById() {

    }

    @GetMapping()
    public List<Employee> getEmployees() {
        return hierarchyService.getEmployeeList();

    }
}
