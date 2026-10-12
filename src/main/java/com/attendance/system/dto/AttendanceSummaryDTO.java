package com.attendance.system.dto;

public class AttendanceSummaryDTO {

    private Long studentId;
    private String rollNumber;
    private String name;
    private String department;
    private Integer semester;
    private long totalClasses;
    private long attendedClasses; // PRESENT or LATE
    private long absentClasses;
    private double attendancePercentage;

    public AttendanceSummaryDTO() {
    }

    public AttendanceSummaryDTO(Long studentId, String rollNumber, String name, String department, Integer semester,
                                long totalClasses, long attendedClasses, long absentClasses, double attendancePercentage) {
        this.studentId = studentId;
        this.rollNumber = rollNumber;
        this.name = name;
        this.department = department;
        this.semester = semester;
        this.totalClasses = totalClasses;
        this.attendedClasses = attendedClasses;
        this.absentClasses = absentClasses;
        this.attendancePercentage = attendancePercentage;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public long getTotalClasses() {
        return totalClasses;
    }

    public void setTotalClasses(long totalClasses) {
        this.totalClasses = totalClasses;
    }

    public long getAttendedClasses() {
        return attendedClasses;
    }

    public void setAttendedClasses(long attendedClasses) {
        this.attendedClasses = attendedClasses;
    }

    public long getAbsentClasses() {
        return absentClasses;
    }

    public void setAbsentClasses(long absentClasses) {
        this.absentClasses = absentClasses;
    }

    public double getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(double attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }
}
