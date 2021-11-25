package com.Personio.Hierarchy.controller;

import com.Personio.Hierarchy.model.Employee;
import com.Personio.Hierarchy.model.dto.GetSupervisorsResponseDto;
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
    EmployeeServiceImpl employeeService;

    @PostMapping
    public Map<String, Set<String>> updateEmployees(@RequestBody Map<String, String> employeeMap) {

       return employeeService.updateEmployees(employeeMap);
    }

    @GetMapping("/{name}")
    public GetSupervisorsResponseDto getEmployeeSupervisorsByName(@PathVariable String name) {

        return employeeService.getSupervisors(name);

    }

    @GetMapping()
    public List<Employee> getEmployees() {
        return employeeService.getEmployeeList();

    }
}
