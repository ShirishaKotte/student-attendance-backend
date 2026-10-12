package com.attendance.system.dto;

public class AuthResponse {

    private Long id;
    private String username;
    private String fullName;
    private String role; // "FACULTY" or "STUDENT"
    private Long studentId; // If student, the corresponding Student ID
    private String rollNumber;
    private String message;

    public AuthResponse() {
    }

    public AuthResponse(Long id, String username, String fullName, String role, Long studentId, String rollNumber, String message) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.role = role;
        this.studentId = studentId;
        this.rollNumber = rollNumber;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
