package com.spring.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@JsonIdentityInfo(property = "id",generator = ObjectIdGenerators.PropertyGenerator.class)
@Entity
public class ClassRoomDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String className;


    @OneToMany(mappedBy = "classRoomDetails",cascade = CascadeType.ALL)
    private List<Student> studentList;


    public ClassRoomDetails() {
    }


    public ClassRoomDetails(Long id, String className, List<Student> studentList) {
        this.id = id;
        this.className = className;
        this.studentList = studentList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
        for (Student student :studentList){
            student.setClassRoomDetails(this);
        }
    }

    @Override
    public String toString() {
        return "ClassRoomDetails{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", studentList=" + studentList +
                '}';
    }
}

