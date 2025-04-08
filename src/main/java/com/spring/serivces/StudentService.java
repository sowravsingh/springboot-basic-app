package com.spring.serivces;


import com.spring.Repository.StudentRepository;
import com.spring.pojos.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;


    public void createStudentTable(){
        studentRepository.createStudentTable();
    }


    public  void insertRow(String name){
        studentRepository.insertUsingPSS(name);
    }

    public void fetchStudentDetails(){
        List<Student> studentDetails = studentRepository.getStudentDetails();

        for (Student student:studentDetails){
            System.out.println(student);
        }

    }


    public Student fetchStudentDetailsByName(String name){
        Student studentByName = studentRepository.getStudentByName(name);
        return studentByName;
    }

}
