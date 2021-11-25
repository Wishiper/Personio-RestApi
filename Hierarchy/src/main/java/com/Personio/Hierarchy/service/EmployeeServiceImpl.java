package com.Personio.Hierarchy.service;

import com.Personio.Hierarchy.model.Employee;
import com.Personio.Hierarchy.model.dto.GetSupervisorsResponseDto;
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
     * @return Hierarchy map of employee and their subordinates
     */
    @Override
    public Map<String, Set<String>> updateEmployees(Map<String, String> employeeMap) {
        createNewEmployees(employeeMap);
        assignSupervisorIds(employeeMap);
        Employee rootEmployee = employeeRepository.findEmployeeBySupervisorId(0);
        Map<String, Set<String>> map = new LinkedHashMap<>();

        return createHieararchy(rootEmployee, map);

    }

    public Map<String, Set<String>> createHieararchy(Employee rootEmployee, Map<String, Set<String>> map) {
        Set<String> subs = rootEmployee.getSubordinateNames();
        if (rootEmployee.getEmployeeId() == 0) {
            map.put(rootEmployee.getName(), subs);
        }
        if (subs.size() == 0) {
            return map;
        }
        for (String employeeName : subs) {
            map.put(rootEmployee.getName(), subs);
            Employee newRoot = employeeRepository.findEmployeeByName(employeeName);
            createHieararchy(newRoot, map);

        }
        return map;
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

    @Override
    public GetSupervisorsResponseDto getSupervisors(String name) {
        GetSupervisorsResponseDto responseDto = new GetSupervisorsResponseDto();

        String supervisorName = getSupervisorName(name);
        String supervisorManager = getSupervisorName(supervisorName);

        responseDto.setEmployeeName(name);
        responseDto.setEmployeeSupervisor(supervisorName);
        responseDto.setSupervisorManager(supervisorManager);

        return responseDto;
    }

    public String getSupervisorName(String employeeName){
        if(employeeName.equals("No Supervisor")){
            return "No Supervisor";
        }
        Employee employee = employeeRepository.findEmployeeByName(employeeName); //TODO add exception if employee doesn't exist
        Optional<Employee> supervisor = employeeRepository.findById(employee.getSupervisorId());
        if(supervisor.isPresent()){
            return supervisor.get().getName();
        }

        return "No Supervisor";

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

            supervisor.getSubordinateNames().add(emp.getName());  // Adding subordinate to supervisor

            employeeRepository.save(supervisor);
            employeeRepository.save(emp);
        }
    }
}
