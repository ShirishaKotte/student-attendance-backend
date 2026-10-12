package com.attendance.system.service;

import com.attendance.system.dto.AuthResponse;
import com.attendance.system.dto.LoginRequest;
import com.attendance.system.dto.RegisterRequest;
import com.attendance.system.entity.Student;
import com.attendance.system.entity.User;
import com.attendance.system.exception.BadRequestException;
import com.attendance.system.repository.StudentRepository;
import com.attendance.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public AuthService(UserRepository userRepository, StudentRepository studentRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public AuthResponse register(RegisterRequest req) {
        if (userRepository.existsByUsername(req.getUsername())) {
            throw new BadRequestException("User with ID/Username '" + req.getUsername() + "' already exists.");
        }

        String role = req.getRole().toUpperCase();
        if (!role.equals("FACULTY") && !role.equals("STUDENT")) {
            throw new BadRequestException("Role must be either FACULTY or STUDENT.");
        }

        Student student = null;
        if (role.equals("STUDENT")) {
            // Check if student entity already exists for this roll number
            Optional<Student> existingStudent = studentRepository.findByRollNumber(req.getUsername());
            if (existingStudent.isPresent()) {
                student = existingStudent.get();
            } else {
                // Auto create matching student profile
                String email = req.getEmail() != null && !req.getEmail().isBlank()
                        ? req.getEmail()
                        : req.getUsername().toLowerCase() + "@college.edu";
                String dept = req.getDepartment() != null ? req.getDepartment() : "Computer Science";
                Integer sem = req.getSemester() != null ? req.getSemester() : 1;

                student = new Student(req.getUsername(), req.getFullName(), email, dept, sem);
                student = studentRepository.save(student);
            }
        }

        User user = new User(req.getUsername(), req.getPassword(), req.getFullName(), role, student);
        user = userRepository.save(user);

        return new AuthResponse(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getRole(),
                student != null ? student.getId() : null,
                student != null ? student.getRollNumber() : null,
                "Registration successful! Please login."
        );
    }

    public AuthResponse login(LoginRequest req) {
        User user = userRepository.findByUsername(req.getUsername())
                .orElseThrow(() -> new BadRequestException("Invalid ID/Username or password."));

        if (!user.getPassword().equals(req.getPassword())) {
            throw new BadRequestException("Invalid ID/Username or password.");
        }

        Long studentId = user.getStudent() != null ? user.getStudent().getId() : null;
        String rollNumber = user.getStudent() != null ? user.getStudent().getRollNumber() : null;

        return new AuthResponse(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getRole(),
                studentId,
                rollNumber,
                "Login successful!"
        );
    }
}
