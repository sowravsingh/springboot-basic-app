package com.spring.serivces;

import com.spring.Entities.Doctor;
import com.spring.Entities.Patient;
import com.spring.Repository.DoctorRepository;
import com.spring.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    private PatientRepository patientRepository;


    @Autowired
    private DoctorRepository doctorRepository;


    public Doctor saveDoctorDetails(Doctor doctor){
        Doctor doctorDetails = doctorRepository.save(doctor);
        return doctorDetails;
    }

    public Patient savePatientDetails(Patient patient){
        Patient patientDetails = patientRepository.save(patient);
        return patient;
    }

    public Patient getPatientDetailsById(Long id){
        Patient patient = patientRepository.findById(id).get();
        return patient;
    }
}
