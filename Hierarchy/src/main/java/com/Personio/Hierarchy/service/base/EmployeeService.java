package com.Personio.Hierarchy.service.base;

import com.Personio.Hierarchy.model.Employee;
import com.Personio.Hierarchy.model.dto.GetSupervisorsResponseDto;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Base methods for the Hierarchy Service.
 */
public interface EmployeeService {

    JSONObject updateEmployees(Map<String, String> employeeMap);

    List<Employee> getEmployeeList();

    GetSupervisorsResponseDto getSupervisors(String name);
}
