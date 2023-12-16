package com.hospital.management.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "hospital_staff")
public class HospitalStaff {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String firstName;
  private String lastName;
  @Column(nullable = false)
  private String email;
  @Column(nullable = false)
  private String password;
  @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
  private List<Patient> patients;
  @OneToOne
  @JoinColumn(name = "role_id", referencedColumnName = "role_id")
  private Role role;

  public Long getId() {
    return id;
  }

  public HospitalStaff setId(Long id) {
    this.id = id;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public HospitalStaff setPassword(String password) {
    this.password = password;
    return this;
  }

  public List<Patient> getPatients() {
    return patients;
  }

  public HospitalStaff setPatients(List<Patient> patients) {
    this.patients = patients;
    return this;
  }

    public String getEmail() {
        return email;
    }

    public HospitalStaff setEmail(String email) {
        this.email = email;
        return this;
    }

  public Role getRole() {
    return role;
  }

  public HospitalStaff setRole(Role role) {
    this.role = role;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public HospitalStaff setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public HospitalStaff setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }
}