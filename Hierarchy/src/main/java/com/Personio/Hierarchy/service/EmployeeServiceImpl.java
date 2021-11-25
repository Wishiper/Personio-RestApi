package com.Personio.Hierarchy.service;

import com.Personio.Hierarchy.model.Employee;
import com.Personio.Hierarchy.model.dto.GetSupervisorsResponseDto;
import com.Personio.Hierarchy.repository.EmployeeRepository;
import com.Personio.Hierarchy.service.base.EmployeeService;
import org.json.JSONObject;
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
    public JSONObject updateEmployees(Map<String, String> employeeMap) {
        createNewEmployees(employeeMap);
        assignSupervisorIds(employeeMap);

        Employee rootEmployee = employeeRepository.findEmployeeBySupervisorId(0);
        JSONObject subEmployees = buildResponse(rootEmployee); // build Hierarchy json from employees

        return new JSONObject().put(rootEmployee.getName(),subEmployees);

    }

    public JSONObject buildResponse(Employee rootEmployee) {

        JSONObject jsonEmployee = new JSONObject();

        Set<String> subs = rootEmployee.getSubordinateNames();
        for (String employeeName : subs) {
            Employee newRoot = employeeRepository.findEmployeeByName(employeeName);
            if (newRoot.getSubordinateNames().size() == 0) {
                jsonEmployee.put(employeeName, new JSONObject());
                continue;
            }
            jsonEmployee.put(employeeName, buildResponse(newRoot));

        }
        return jsonEmployee;
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
