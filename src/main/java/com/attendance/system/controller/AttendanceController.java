package com.attendance.system.controller;

import com.attendance.system.dto.AttendanceRecordDTO;
import com.attendance.system.dto.AttendanceSummaryDTO;
import com.attendance.system.dto.BatchAttendanceRequest;
import com.attendance.system.entity.Attendance;
import com.attendance.system.service.AttendanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "*")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // CREATE / BATCH UPDATE: Record attendance for whole class on a given date
    @PostMapping("/batch")
    public ResponseEntity<List<Attendance>> markBatchAttendance(@Valid @RequestBody BatchAttendanceRequest request) {
        List<Attendance> savedList = attendanceService.markBatchAttendance(request);
        return new ResponseEntity<>(savedList, HttpStatus.CREATED);
    }

    // CREATE / UPDATE: Record or update single student attendance
    @PostMapping("/single")
    public ResponseEntity<Attendance> markSingleAttendance(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @Valid @RequestBody AttendanceRecordDTO recordDTO) {
        Attendance saved = attendanceService.markOrUpdateSingleAttendance(recordDTO, date);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // READ: Get attendance records for a specific date
    @GetMapping("/date/{date}")
    public ResponseEntity<List<Attendance>> getAttendanceByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(attendanceService.getAttendanceByDate(date));
    }

    // READ: Get attendance history for a single student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Attendance>> getAttendanceByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(attendanceService.getAttendanceByStudentId(studentId));
    }

    // READ: Get overall summary & percentage for all students
    @GetMapping("/summary")
    public ResponseEntity<List<AttendanceSummaryDTO>> getAllAttendanceSummaries() {
        return ResponseEntity.ok(attendanceService.getAllStudentsAttendanceSummary());
    }

    // READ: Get individual student attendance summary & percentage
    @GetMapping("/summary/{studentId}")
    public ResponseEntity<AttendanceSummaryDTO> getStudentSummary(@PathVariable Long studentId) {
        return ResponseEntity.ok(attendanceService.getStudentAttendanceSummary(studentId));
    }

    // UPDATE: Update attendance record status
    @PutMapping("/{id}")
    public ResponseEntity<Attendance> updateAttendance(
            @PathVariable Long id,
            @RequestBody Map<String, String> updatePayload) {
        String status = updatePayload.get("status");
        String remarks = updatePayload.get("remarks");
        Attendance updated = attendanceService.updateAttendanceStatus(id, status, remarks);
        return ResponseEntity.ok(updated);
    }

    // DELETE: Delete an attendance record
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.noContent().build();
    }
}
