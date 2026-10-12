package com.attendance.system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username/ID is required")
    @Column(nullable = false, unique = true)
    private String username; // Roll Number for students, or ID/username for faculty

    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Full name is required")
    @Column(nullable = false)
    private String fullName;

    @NotBlank(message = "Role is required")
    @Column(nullable = false)
    private String role; // "FACULTY" or "STUDENT"

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", nullable = true)
    private Student student; // Linked student record if role == STUDENT

    public User() {
    }

    public User(String username, String password, String fullName, String role, Student student) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.student = student;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
