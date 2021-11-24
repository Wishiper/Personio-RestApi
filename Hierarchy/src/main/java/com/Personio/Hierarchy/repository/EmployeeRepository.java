package com.Personio.Hierarchy.repository;

import com.Personio.Hierarchy.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for handling employee information.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findEmployeeByName(String name);

    boolean existsByName(String name);
}


