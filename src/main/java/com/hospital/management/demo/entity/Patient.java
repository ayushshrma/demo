package com.hospital.management.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "patient")
public class Patient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long patientId;

  @Column(nullable = false)
  private String name;

  private Integer age;

  private Integer roomNumber;

  @ManyToOne
  @JoinColumn(name = "doctor_id", nullable = false)
  private HospitalStaff doctor;

  private LocalDate admitDate;

  private BigDecimal expenses;

  private String status;

  public Long getPatientId() {
    return patientId;
  }

  public Patient setPatientId(Long patientId) {
    this.patientId = patientId;
    return this;
  }

  public String getName() {
    return name;
  }

  public Patient setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getAge() {
    return age;
  }

  public Patient setAge(Integer age) {
    this.age = age;
    return this;
  }

  public Integer getRoomNumber() {
    return roomNumber;
  }

  public Patient setRoomNumber(Integer roomNumber) {
    this.roomNumber = roomNumber;
    return this;
  }

  public HospitalStaff getDoctor() {
    return doctor;
  }

  public Patient setDoctor(HospitalStaff doctor) {
    this.doctor = doctor;
    return this;
  }

  public LocalDate getAdmitDate() {
    return admitDate;
  }

  public Patient setAdmitDate(LocalDate admitDate) {
    this.admitDate = admitDate;
    return this;
  }

  public BigDecimal getExpenses() {
    return expenses;
  }

  public Patient setExpenses(BigDecimal expenses) {
    this.expenses = expenses;
    return this;
  }

  public String getStatus() {
    return status;
  }

  public Patient setStatus(String status) {
    this.status = status;
    return this;
  }
}
