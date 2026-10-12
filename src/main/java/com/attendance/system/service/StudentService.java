package com.attendance.system.service;

import com.attendance.system.entity.Student;
import com.attendance.system.exception.BadRequestException;
import com.attendance.system.exception.ResourceNotFoundException;
import com.attendance.system.repository.AttendanceRepository;
import com.attendance.system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final AttendanceRepository attendanceRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, AttendanceRepository attendanceRepository) {
        this.studentRepository = studentRepository;
        this.attendanceRepository = attendanceRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));
    }

    public Student createStudent(Student student) {
        if (studentRepository.existsByRollNumber(student.getRollNumber())) {
            throw new BadRequestException("Student with roll number " + student.getRollNumber() + " already exists.");
        }
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new BadRequestException("Student with email " + student.getEmail() + " already exists.");
        }
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student existing = getStudentById(id);

        if (!existing.getRollNumber().equalsIgnoreCase(studentDetails.getRollNumber()) &&
                studentRepository.existsByRollNumber(studentDetails.getRollNumber())) {
            throw new BadRequestException("Roll number " + studentDetails.getRollNumber() + " is already taken.");
        }

        if (!existing.getEmail().equalsIgnoreCase(studentDetails.getEmail()) &&
                studentRepository.existsByEmail(studentDetails.getEmail())) {
            throw new BadRequestException("Email " + studentDetails.getEmail() + " is already registered.");
        }

        existing.setRollNumber(studentDetails.getRollNumber());
        existing.setName(studentDetails.getName());
        existing.setEmail(studentDetails.getEmail());
        existing.setDepartment(studentDetails.getDepartment());
        existing.setSemester(studentDetails.getSemester());

        return studentRepository.save(existing);
    }

    @Transactional
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        attendanceRepository.deleteByStudentId(student.getId());
        studentRepository.delete(student);
    }
}
