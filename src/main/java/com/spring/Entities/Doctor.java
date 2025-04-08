package com.spring.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Doctor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;

    private String doctorName;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "doctor_patient", joinColumns = @JoinColumn(name  ="doctor_id"), inverseJoinColumns = @JoinColumn(name = "patient_id"))
    private List<Patient> patientList =new ArrayList<>();

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public List<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }
}
