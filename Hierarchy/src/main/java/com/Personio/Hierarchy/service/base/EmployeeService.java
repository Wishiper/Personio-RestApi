package com.Personio.Hierarchy.service.base;

import com.Personio.Hierarchy.model.Employee;
import com.Personio.Hierarchy.model.dto.GetSupervisorsResponseDto;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Base methods for the Hierarchy Service.
 */
public interface EmployeeService {

    Map<String, Set<String>> updateEmployees(Map<String, String> employeeMap);

    List<Employee> getEmployeeList();

    GetSupervisorsResponseDto getSupervisors(String name);
}
