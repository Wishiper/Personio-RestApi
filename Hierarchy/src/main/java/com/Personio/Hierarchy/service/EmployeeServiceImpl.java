package com.Personio.Hierarchy.service;

import com.Personio.Hierarchy.model.Employee;
import com.Personio.Hierarchy.repository.EmployeeRepository;
import com.Personio.Hierarchy.service.base.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    /**
     * Creates new employees and assigns their supervisors.
     *
     * @param employeeMap Map of employees and their supervisors.
     */
    @Override
    public void updateEmployees(Map<String, String> employeeMap) {
        createNewEmployees(employeeMap);
        assignSupervisorIds(employeeMap);

    }

    /**
     * Gets all employees saved in the database.
     *
     * @return List of Employee.
     */
    @Override
    public List<Employee> getEmployeeList() {
        return employeeRepository.findAll();
    }

    /**
     * Creates new employees only with unique names, that don't already exist in the DB.
     *
     * @param employeeMap Map of employees and their supervisors.
     */
    void createNewEmployees(Map<String, String> employeeMap) {
        Set<String> employeeSet = new TreeSet<>();
        employeeMap.forEach((key, value) -> {
            employeeSet.add(key);
            employeeSet.add(value);
        });

        for (String employeeName : employeeSet) {
            if (!employeeRepository.existsByName(employeeName)) {
                Employee newEmployee = new Employee();
                newEmployee.setName(employeeName);
                employeeRepository.save(newEmployee);
            }
        }
    }

    /**
     * Goes through employee map and assigns a supervisor id to every employee.
     *
     * @param employeeMap Map of employees and their supervisors.
     */
    void assignSupervisorIds(Map<String, String> employeeMap) {
        for (Map.Entry<String, String> employee : employeeMap.entrySet()) {
            Employee emp = employeeRepository.findEmployeeByName(employee.getKey());
            Employee supervisor = employeeRepository.findEmployeeByName(employee.getValue());
            emp.setSupervisorId(supervisor.getEmployeeId());
            employeeRepository.save(emp);
        }
    }
}
