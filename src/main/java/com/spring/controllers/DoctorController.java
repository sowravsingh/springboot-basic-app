package com.spring.controllers;

import com.spring.Entities.Doctor;
import com.spring.Entities.Patient;
import com.spring.serivces.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("api/savePatientDetails")
    public ResponseEntity<Patient> savePatientDetails(@RequestBody Patient patient){
        Patient savedDetails = doctorService.savePatientDetails(patient);
        return ResponseEntity.status(HttpStatus.OK).body(savedDetails);
    }


    @PostMapping("api/saveDoctorDetails")
    public ResponseEntity<Doctor> saveDoctorDetails(@RequestBody Doctor doctor){
        Doctor savedDoctorDetails = doctorService.saveDoctorDetails(doctor);

        return ResponseEntity.status(HttpStatus.OK).body(savedDoctorDetails);
    }

    @PostMapping("api/saveDoctorDetailsByPatientID")
    public ResponseEntity<Doctor> saveDoctorDetailsByPatientId(@RequestBody Doctor doctor){
        List<Patient> patientList = doctor.getPatientList().stream().map(patient ->doctorService.getPatientDetailsById(patient.getPatientId())).collect(Collectors.toList());
        doctor.setPatientList(patientList);
        Doctor savedDoctorDetails = doctorService.saveDoctorDetails(doctor);

        return ResponseEntity.status(HttpStatus.OK).body(savedDoctorDetails);
    }


}
