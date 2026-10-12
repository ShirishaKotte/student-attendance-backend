package com.attendance.system.dto;

import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {

    @NotBlank(message = "Username or ID/Roll Number is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Role must be FACULTY or STUDENT")
    private String role; // "FACULTY" or "STUDENT"

    private String email;
    private String department;
    private Integer semester;

    public RegisterRequest() {
    }

    public RegisterRequest(String username, String password, String fullName, String role, String email, String department, Integer semester) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.email = email;
        this.department = department;
        this.semester = semester;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }
}
