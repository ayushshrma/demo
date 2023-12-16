package com.hospital.management.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class PatientDto {
    @NotEmpty(message = "First Name is mandatory.")
    @Size(min = 2, max = 30, message = "Must contain 2-15 characters.")
    @Pattern(regexp="(^[A-Za-z]*$)",message = "Invalid Input. " +
        "First Name can only contain alphabets.")
    private String name;
    @NotNull(message = "Age cannot be null")
    @Positive(message = "Age must be a positive number")
    @Min(value = 1, message = "Age must be at least 1")
    @Max(value = 100, message = "Age must not exceed 150")
    private Integer age;
    @NotEmpty(message = "roomNumber cannot be empty")
    @NotNull(message = "roomNumber cannot be null")
    private Integer roomNumber;
    private Long doctorId;
    private LocalDate admitDate;
    private BigDecimal expenses;
    @NotEmpty(message = "Status cannot be empty")
    @NotNull(message = "Status cannot be null")
    @Pattern(regexp = "^(admitted|discharged)$", message = "Status must be either 'admitted' or 'discharged'")
    private String status;

    public PatientDto() {
    }

    public PatientDto(String name, Integer age, Integer roomNumber, Long doctorId, String doctorName,
                      LocalDate admitDate, BigDecimal expenses, String status) {
        this.name = name;
        this.age = age;
        this.roomNumber = roomNumber;
        this.doctorId = doctorId;
        this.admitDate = admitDate;
        this.expenses = expenses;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getAdmitDate() {
        return admitDate;
    }

    public void setAdmitDate(LocalDate admitDate) {
        this.admitDate = admitDate;
    }

    public BigDecimal getExpenses() {
        return expenses;
    }

    public void setExpenses(BigDecimal expenses) {
        this.expenses = expenses;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
