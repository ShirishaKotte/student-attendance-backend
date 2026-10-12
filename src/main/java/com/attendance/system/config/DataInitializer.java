package com.attendance.system.config;

import com.attendance.system.entity.Attendance;
import com.attendance.system.entity.Student;
import com.attendance.system.entity.User;
import com.attendance.system.repository.AttendanceRepository;
import com.attendance.system.repository.StudentRepository;
import com.attendance.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final AttendanceRepository attendanceRepository;

    @Autowired
    public DataInitializer(UserRepository userRepository, StudentRepository studentRepository, AttendanceRepository attendanceRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Only initialize sample data if database is empty
        if (studentRepository.count() == 0) {
            System.out.println("Initializing starter demo data...");

            Student s1 = studentRepository.save(new Student("23CS001", "Rahul Sharma", "rahul.sharma@example.com", "Computer Science", 6));
            Student s2 = studentRepository.save(new Student("23CS002", "Priya Patel", "priya.patel@example.com", "Computer Science", 6));
            Student s3 = studentRepository.save(new Student("23EC015", "Kiran Kumar", "kiran.kumar@example.com", "Electronics & Comm", 6));
            Student s4 = studentRepository.save(new Student("23IT021", "Ananya Reddy", "ananya.reddy@example.com", "Information Tech", 6));
            Student s5 = studentRepository.save(new Student("23CS045", "Sneha Verma", "sneha.verma@example.com", "Computer Science", 6));

            // Faculty User
            userRepository.save(new User("faculty101", "password123", "Prof. Rajesh Sharma", "FACULTY", null));

            // Student Users
            userRepository.save(new User("23CS001", "password123", "Rahul Sharma", "STUDENT", s1));
            userRepository.save(new User("23CS002", "password123", "Priya Patel", "STUDENT", s2));

            // Sample Attendance
            attendanceRepository.save(new Attendance(s1, LocalDate.now().minusDays(3), "PRESENT", "On time"));
            attendanceRepository.save(new Attendance(s1, LocalDate.now().minusDays(2), "PRESENT", "On time"));
            attendanceRepository.save(new Attendance(s1, LocalDate.now().minusDays(1), "ABSENT", "Sick leave"));
            attendanceRepository.save(new Attendance(s1, LocalDate.now(), "PRESENT", "On time"));

            attendanceRepository.save(new Attendance(s2, LocalDate.now().minusDays(1), "PRESENT", "On time"));
            attendanceRepository.save(new Attendance(s2, LocalDate.now(), "ABSENT", "Excused"));

            System.out.println("Demo data initialized successfully!");
        }
    }
}
