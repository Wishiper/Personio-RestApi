package com.Personio.Hierarchy.model;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int employeeId;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "supervisorId")
    private int supervisorId;

    @ElementCollection
    @Column(name = "subordinateNames")
    Set<String> subordinateNames = new TreeSet<>();

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }

    public Set<String> getSubordinateNames() {
        return subordinateNames;
    }

    public void setSubordinateNames(Set<String> subordinateNames) {
        this.subordinateNames = subordinateNames;
    }
}
