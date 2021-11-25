package com.Personio.Hierarchy.controller;

import com.Personio.Hierarchy.model.Employee;
import com.Personio.Hierarchy.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl hierarchyService;

    @PostMapping
    public Map<String, Set<String>> updateEmployees(@RequestBody Map<String, String> employeeMap) {

       return hierarchyService.updateEmployees(employeeMap);
    }

    @GetMapping("/id")
    public void getEmployeeById() {

    }

    @GetMapping()
    public List<Employee> getEmployees() {
        return hierarchyService.getEmployeeList();

    }
}
