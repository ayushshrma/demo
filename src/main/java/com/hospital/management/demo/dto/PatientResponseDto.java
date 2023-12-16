package com.hospital.management.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PatientResponseDto {
    private String name;
    private Integer age;
    private Integer roomNumber;
    private String doctorName;
    private LocalDate admitDate;
    private BigDecimal expenses;
    private String status;

    public String getName() {
        return name;
    }

    public PatientResponseDto setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public PatientResponseDto setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public PatientResponseDto setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
        return this;
    }
    public LocalDate getAdmitDate() {
        return admitDate;
    }

    public PatientResponseDto setAdmitDate(LocalDate admitDate) {
        this.admitDate = admitDate;
        return this;
    }

    public BigDecimal getExpenses() {
        return expenses;
    }

    public PatientResponseDto setExpenses(BigDecimal expenses) {
        this.expenses = expenses;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public PatientResponseDto setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public PatientResponseDto setDoctorName(String doctorName) {
        this.doctorName = doctorName;
        return this;
    }
}