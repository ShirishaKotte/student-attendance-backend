package com.attendance.system.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class BatchAttendanceRequest {

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotEmpty(message = "Attendance records cannot be empty")
    @Valid
    private List<AttendanceRecordDTO> records;

    public BatchAttendanceRequest() {
    }

    public BatchAttendanceRequest(LocalDate date, List<AttendanceRecordDTO> records) {
        this.date = date;
        this.records = records;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<AttendanceRecordDTO> getRecords() {
        return records;
    }

    public void setRecords(List<AttendanceRecordDTO> records) {
        this.records = records;
    }
}
