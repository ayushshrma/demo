package com.hospital.management.demo.service;

import com.hospital.management.demo.dto.PatientDto;
import com.hospital.management.demo.dto.PatientResponseDto;
import com.hospital.management.demo.entity.Patient;
import java.util.List;

public interface PatientService {

  List<Long> registerPatient(List<PatientDto> patientDto);

  List<PatientResponseDto> getAllPatients();

  List<Boolean> dischargePatient(List<Long> patientId);
}