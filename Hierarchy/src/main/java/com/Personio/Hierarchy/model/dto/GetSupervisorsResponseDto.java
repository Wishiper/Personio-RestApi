package com.Personio.Hierarchy.model.dto;

public class GetSupervisorsResponseDto {

    private String employeeName;
    private String employeeSupervisor;
    private String supervisorManager;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeSupervisor() {
        return employeeSupervisor;
    }

    public void setEmployeeSupervisor(String employeeSupervisor) {
        this.employeeSupervisor = employeeSupervisor;
    }

    public String getSupervisorManager() {
        return supervisorManager;
    }

    public void setSupervisorManager(String supervisorManager) {
        this.supervisorManager = supervisorManager;
    }
}
