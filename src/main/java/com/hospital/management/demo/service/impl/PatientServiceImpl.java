package com.hospital.management.demo.service.impl;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

import com.hospital.management.demo.dto.PatientDto;
import com.hospital.management.demo.dto.PatientResponseDto;
import com.hospital.management.demo.entity.HospitalStaff;
import com.hospital.management.demo.entity.Patient;
import com.hospital.management.demo.repositories.HospitalStaffRepository;
import com.hospital.management.demo.repositories.PatientRepository;
import com.hospital.management.demo.service.PatientService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PatientServiceImpl implements PatientService {

  @Autowired
  private HospitalStaffRepository hospitalStaffRepository;
  @Autowired
  private PatientRepository patientRepository;

  @Transactional
  public List<Long> registerPatient(List<PatientDto> patientDtos) {
    List<Patient> patients = patientDtos.stream().map(patientDto -> {
      HospitalStaff doctor = hospitalStaffRepository.getDoctorById(patientDto.getDoctorId());
      Patient patient = convertDtoToEntity(patientDto);
      patient.setDoctor(doctor);
      return patient;
    }).collect(toList());
    patientRepository.saveAll(patients);
    // Update the doctors' list of patients
    List<HospitalStaff> doctors = patients.stream().map(Patient::getDoctor).collect(toList());
    doctors.forEach(doctor -> {
      List<Patient> doctorPatients = patients.stream()
          .filter(patient -> doctor.equals(patient.getDoctor())).collect(Collectors.toList());
      doctor.getPatients().addAll(doctorPatients);
      hospitalStaffRepository.save(doctor);
    });
    return patients.stream().map(Patient::getPatientId).collect(Collectors.toList());
  }

  private Patient convertDtoToEntity(PatientDto patientDto) {
    return new Patient().setName(patientDto.getName()).setAge(patientDto.getAge())
        .setRoomNumber(patientDto.getRoomNumber()).setAdmitDate(patientDto.getAdmitDate())
        .setExpenses(patientDto.getExpenses()).setStatus(patientDto.getStatus());
  }

  public List<PatientResponseDto> getAllPatients() {
    List<Patient> patients =  patientRepository.findAll();
    return patients.stream()
        .map(this::convertToDto)
        .collect(Collectors.toList());
  }
  public List<Boolean> dischargePatient(List<Long> patientIds) {
    List<Patient> patients = patientRepository.findAllById(patientIds);
    return patients.stream().map(patient -> {
      if ("discharged".equalsIgnoreCase(patient.getStatus())) {
        return false;
      }
      patient.setStatus("discharged");
      patientRepository.save(patient);
      return true;
    }).collect(Collectors.toList());
  }
  public PatientResponseDto convertToDto(Patient patient) {
    PatientResponseDto patientResponseDto = new PatientResponseDto();
    patientResponseDto.setName(patient.getName());
    patientResponseDto.setAge(patient.getAge());
    patientResponseDto.setRoomNumber(patient.getRoomNumber());
    patientResponseDto.setDoctorName(ofNullable(patient.getDoctor())
        .map(HospitalStaff::getFirstName).orElse(null));
    patientResponseDto.setAdmitDate(patient.getAdmitDate());
    patientResponseDto.setExpenses(patient.getExpenses());
    patientResponseDto.setStatus(patient.getStatus());

    return patientResponseDto;
  }
}
