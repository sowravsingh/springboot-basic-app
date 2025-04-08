package com.spring.controllers;


import com.spring.Entities.ClassRoomDetails;
import com.spring.Entities.OrderDetails;
import com.spring.Repository.OrderRepository;
import com.spring.pojos.Student;
import com.spring.serivces.ClassRoomService;
import com.spring.serivces.OrderDetailsService;
import com.spring.serivces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    OrderDetailsService orderDetailsService;

    @Autowired
    ClassRoomService classRoomService;

    @GetMapping("/api/createStudentRepo")
    public ResponseEntity<String> createStudentRepo(){
        studentService.createStudentTable();
        studentService.insertRow("sowrav");
        studentService.insertRow("teja");
        studentService.fetchStudentDetails();

        return   ResponseEntity.status(HttpStatus.CREATED).body("repoCreatedSuccessfully");
    }

    @GetMapping("/api/getStudentByName")
    public ResponseEntity<Student> getStudentByName(@RequestParam(value = "name") String name){
        Student student = studentService.fetchStudentDetailsByName(name);

        return ResponseEntity.status(HttpStatus.OK).body(student);

    }

    @GetMapping("/api/JPATestApi")
    public ResponseEntity<String> getOrderDetails(){

        OrderDetails orderDetails1  = new OrderDetails("offlineOrder","sowravsingh@gmail.com","8825911876");
        OrderDetails orderDetails2  = new OrderDetails("onlineOrder","sowravsingh@gmail.com","8825911876");
        orderDetailsService.saveOrderDetails(orderDetails1);
        orderDetailsService.saveOrderDetails(orderDetails2);
        return  ResponseEntity.status(HttpStatus.OK).body("data recieved successfully");
    }


    @PostMapping("/api/insertOrderDetails")
    public ResponseEntity<String> insertOrder(@RequestBody Map<String,Object> requestBody){
        String orderType = (String) requestBody.get("orderType");
        String email = (String)  requestBody.get("email");
        String phoneNo =(String) requestBody.get("phone");

        OrderDetails orderDetails = new OrderDetails(orderType,email,phoneNo);
        orderDetailsService.saveOrderDetails(orderDetails);
        return ResponseEntity.status(HttpStatus.OK).body("rowInserted");
    }

    @GetMapping("/api/JPAGetApi")
    public ResponseEntity<OrderDetails> getOrderDetailsById(){
        OrderDetails orderDetails = orderDetailsService.getOrderDetailsById(1L);
        return  ResponseEntity.status(HttpStatus.OK).body(orderDetails);
    }

    @GetMapping("/api/testMultipleEntityManager")
    public ResponseEntity<String> getOrderDetailsByMultipleEntityManager(){
        orderDetailsService.testMultipleEntityManagers();
        return  ResponseEntity.status(HttpStatus.OK).body("success");
    }


    @PostMapping("/api/saveClassDetails")
    public ResponseEntity<String> saveClassDetails(@RequestBody ClassRoomDetails classRoomDetails){
        System.out.println("class room details "+classRoomDetails);
        classRoomService.saveClassDetails(classRoomDetails);
        return ResponseEntity.status(HttpStatus.OK).body("class details inserted successfully");
    }


    @GetMapping("/api/getClassDetailsById")
    public ResponseEntity<ClassRoomDetails> getClassDetails(@RequestParam (name = "id") Long id){
        ClassRoomDetails classRoomDetails = classRoomService.getClassRoomDetails(id);
        return ResponseEntity.status(HttpStatus.OK).body(classRoomDetails);
    }

    @PostMapping("/api/saveStudentDetails")
    public ResponseEntity<String> saveStudentDetails(@RequestBody com.spring.Entities.Student student){
        classRoomService.saveStudentDetails(student);
        return ResponseEntity.status(HttpStatus.OK).body("studentDetails inserted successfully");
    }

    @GetMapping("/api/getStudentDetails")
    public ResponseEntity<com.spring.Entities.Student> getStudentDetails(@RequestParam (name = "id") Long id){
        com.spring.Entities.Student student = classRoomService.getStudentDetails(id);
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @PostMapping("/api/updateClassDetails")
    public ResponseEntity<String> updateClassRoomDetails(@RequestParam (name = "id") Long id){
        ClassRoomDetails classRoomDetails = classRoomService.getClassRoomDetails(id);
        classRoomDetails.getStudentList().remove(0);
        classRoomService.saveClassDetails(classRoomDetails);
        return ResponseEntity.status(HttpStatus.OK).body("updated successfully");
    }


}

