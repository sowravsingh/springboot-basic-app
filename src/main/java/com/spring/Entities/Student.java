package com.spring.Entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;


@JsonIdentityInfo(property = "id",generator = ObjectIdGenerators.PropertyGenerator.class)
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String studentName;


    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private ClassRoomDetails classRoomDetails;

    public ClassRoomDetails getClassRoomDetails() {
        return classRoomDetails;
    }

    public void setClassRoomDetails(ClassRoomDetails classRoomDetails) {
        this.classRoomDetails = classRoomDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Student() {
    }

    public Student(Long id, String studentName) {
        this.id = id;
        this.studentName = studentName;
    }
}
