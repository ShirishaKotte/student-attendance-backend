package com.attendance.system.dto;

import jakarta.validation.constraints.NotNull;

public class AttendanceRecordDTO {

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Status is required (PRESENT, ABSENT, or LATE)")
    private String status;

    private String remarks;

    public AttendanceRecordDTO() {
    }

    public AttendanceRecordDTO(Long studentId, String status, String remarks) {
        this.studentId = studentId;
        this.status = status;
        this.remarks = remarks;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
