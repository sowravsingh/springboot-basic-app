package com.spring.serivces;

import com.spring.Entities.ClassRoomDetails;
import com.spring.Entities.Student;
import com.spring.Repository.ClassRoomRepository;
import com.spring.Repository.StudentDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClassRoomService {

    @Autowired
    public ClassRoomRepository classRoomRepository;


    @Autowired
    public StudentDetailsRepository studentDetailsRepository;


    public void  saveClassDetails(ClassRoomDetails classRoomDetails){
        classRoomRepository.save(classRoomDetails);
    }


    public ClassRoomDetails getClassRoomDetails(Long id){
        ClassRoomDetails classRoomDetails= classRoomRepository.findById(id).get();
        return classRoomDetails;
    }

    public void saveStudentDetails(Student student){
        studentDetailsRepository.save(student);
    }


    public Student getStudentDetails(Long id){
        Student student = studentDetailsRepository.findById(id).get();
        return student;
    }
}
