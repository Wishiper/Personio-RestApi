package com.Personio.Hierarchy.controller;

import com.Personio.Hierarchy.model.Employee;
import com.Personio.Hierarchy.model.dto.GetSupervisorsResponseDto;
import com.Personio.Hierarchy.service.EmployeeServiceImpl;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> updateEmployees(@RequestBody Map<String, String> employeeMap) {

        JSONObject jsObj = employeeService.updateEmployees(employeeMap);
        return new ResponseEntity<>(jsObj.toMap(), HttpStatus.OK);
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
