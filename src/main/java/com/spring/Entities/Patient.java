package com.spring.Entities;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    private String patientName;

//    @ManyToMany(mappedBy = "patientList")
//    @JsonIgnore
//    private List<Doctor> doctorList =new ArrayList<>();
//
//    public List<Doctor> getDoctorList() {
//        return doctorList;
//    }
//
//    public void setDoctorList(List<Doctor> doctorList) {
//        this.doctorList = doctorList;
//    }

    public String getPatientName() {
        return patientName;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}
