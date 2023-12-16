package com.hospital.management.demo.controller;

import static java.util.stream.Collectors.toList;

import com.hospital.management.demo.dto.PatientDto;
import com.hospital.management.demo.dto.PatientResponseDto;
import com.hospital.management.demo.service.PatientService;
import java.util.List;
import java.util.stream.IntStream;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospital-management/patient")
public class PatientController {

  @Autowired
  private PatientService patientService;

  @PostMapping("/add")
  public ResponseEntity<String> registerPatient(@Valid @RequestBody List<PatientDto> patientDto) {
    List<Long> patientIds = patientService.registerPatient(patientDto);
    return new ResponseEntity<>("Patient registered successfully.Patient id:" + patientIds,
        HttpStatus.CREATED);
  }

  @GetMapping("/fetch")
  public ResponseEntity<List<PatientResponseDto>> getAllPatients() {
    List<PatientResponseDto> patients = patientService.getAllPatients();
    return ResponseEntity.ok(patients);
  }

  @PutMapping("/discharge")
  public ResponseEntity<String> dischargePatient(@RequestBody List<Long> patientIds) {
    try {
      List<Boolean> dischargeResults = patientService.dischargePatient(patientIds);
      List<String> resultMessages = IntStream.range(0, patientIds.size()).mapToObj(i -> {
        String status = Boolean.TRUE.equals(dischargeResults.get(i)) ? "discharged successfully" : "is already discharged";
        return "Patient with ID " + patientIds.get(i) + " " + status + ".";
      }).collect(toList());
      String responseMessage = String.join("\n", resultMessages);
      return ResponseEntity.ok(
          "Patients discharged successfully. Discharge results:\n" + responseMessage);
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Internal server error");
    }
  }
}
