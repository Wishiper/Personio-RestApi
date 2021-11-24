package com.Personio.Hierarchy.service.base;

import com.Personio.Hierarchy.model.Employee;

import java.util.List;
import java.util.Map;

/**
 * Base methods for the Hierarchy Service.
 */
public interface EmployeeService {

    void updateEmployees(Map<String, String> employeeMap);

    List<Employee> getEmployeeList();
}
