package com.model;

public class UserSessionDTO {
    private String employeeId;
    private String requestType;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
}
