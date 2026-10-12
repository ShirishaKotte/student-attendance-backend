package com.attendance.system.service;

import com.attendance.system.dto.AttendanceRecordDTO;
import com.attendance.system.dto.AttendanceSummaryDTO;
import com.attendance.system.dto.BatchAttendanceRequest;
import com.attendance.system.entity.Attendance;
import com.attendance.system.entity.Student;
import com.attendance.system.exception.ResourceNotFoundException;
import com.attendance.system.repository.AttendanceRepository;
import com.attendance.system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository, StudentRepository studentRepository) {
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
    }

    public List<Attendance> getAttendanceByDate(LocalDate date) {
        return attendanceRepository.findByDate(date);
    }

    public List<Attendance> getAttendanceByStudentId(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student not found with ID: " + studentId);
        }
        return attendanceRepository.findByStudentIdOrderByDateDesc(studentId);
    }

    @Transactional
    public Attendance markOrUpdateSingleAttendance(AttendanceRecordDTO dto, LocalDate date) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + dto.getStudentId()));

        Optional<Attendance> existingOpt = attendanceRepository.findByStudentIdAndDate(student.getId(), date);
        Attendance attendance;
        if (existingOpt.isPresent()) {
            attendance = existingOpt.get();
            attendance.setStatus(dto.getStatus().toUpperCase());
            attendance.setRemarks(dto.getRemarks());
        } else {
            attendance = new Attendance(student, date, dto.getStatus().toUpperCase(), dto.getRemarks());
        }

        return attendanceRepository.save(attendance);
    }

    @Transactional
    public List<Attendance> markBatchAttendance(BatchAttendanceRequest request) {
        LocalDate date = request.getDate();
        List<Attendance> savedList = new ArrayList<>();

        for (AttendanceRecordDTO recordDTO : request.getRecords()) {
            Student student = studentRepository.findById(recordDTO.getStudentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + recordDTO.getStudentId()));

            Optional<Attendance> existingOpt = attendanceRepository.findByStudentIdAndDate(student.getId(), date);
            Attendance attendance;
            if (existingOpt.isPresent()) {
                attendance = existingOpt.get();
                attendance.setStatus(recordDTO.getStatus().toUpperCase());
                attendance.setRemarks(recordDTO.getRemarks());
            } else {
                attendance = new Attendance(student, date, recordDTO.getStatus().toUpperCase(), recordDTO.getRemarks());
            }

            savedList.add(attendanceRepository.save(attendance));
        }

        return savedList;
    }

    public Attendance updateAttendanceStatus(Long id, String status, String remarks) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance record not found with ID: " + id));

        attendance.setStatus(status.toUpperCase());
        if (remarks != null) {
            attendance.setRemarks(remarks);
        }
        return attendanceRepository.save(attendance);
    }

    public void deleteAttendance(Long id) {
        if (!attendanceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Attendance record not found with ID: " + id);
        }
        attendanceRepository.deleteById(id);
    }

    public AttendanceSummaryDTO getStudentAttendanceSummary(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + studentId));

        long total = attendanceRepository.countByStudentId(studentId);
        long present = attendanceRepository.countByStudentIdAndStatus(studentId, "PRESENT");
        long late = attendanceRepository.countByStudentIdAndStatus(studentId, "LATE");
        long attended = present + late;
        long absent = total - attended;

        double percentage = total > 0 ? Math.round(((double) attended / total) * 10000.0) / 100.0 : 0.0;

        return new AttendanceSummaryDTO(
                student.getId(),
                student.getRollNumber(),
                student.getName(),
                student.getDepartment(),
                student.getSemester(),
                total,
                attended,
                absent,
                percentage
        );
    }

    public List<AttendanceSummaryDTO> getAllStudentsAttendanceSummary() {
        List<Student> students = studentRepository.findAll();
        List<AttendanceSummaryDTO> summaries = new ArrayList<>();

        for (Student student : students) {
            summaries.add(getStudentAttendanceSummary(student.getId()));
        }

        return summaries;
    }
}
