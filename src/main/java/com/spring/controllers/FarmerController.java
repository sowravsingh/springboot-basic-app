package com.spring.controllers;


import com.spring.DTO.FarmerDto;
import com.spring.DTO.FarmerDto2;
import com.spring.Entities.FarmerDetails;
import com.spring.serivces.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FarmerController {

    @Autowired
    FarmerService farmerService;


    @PostMapping("/api/insertFarmerDetails")
    public ResponseEntity<String> saveFarmerDetails(@RequestBody FarmerDetails farmerDetails){
        farmerService.saveFarmerDetails(farmerDetails);
        return  ResponseEntity.status(HttpStatus.OK).body("Details inserted successfully");
    }

    @GetMapping("/api/getFarmerDetailsById")
    public ResponseEntity<FarmerDetails> getFarmerDetailsById(@RequestParam(name = "id") Long id){
        System.out.println("id is "+id);
        FarmerDetails farmerDetailsById = farmerService.getFarmerDetailsById(id);

        return  ResponseEntity.status(HttpStatus.OK).body(farmerDetailsById);
    }

    @GetMapping("/api/deleteFarmerDetailsByName")
    public ResponseEntity<String> deleteFarmerDetailsByName(@RequestParam(name = "name") String name){
        farmerService.deleteFarmerDataByName(name);
        return  ResponseEntity.status(HttpStatus.OK).body("row deleted successfully");
    }

    @GetMapping("/api/getFarmerDetailsByName")
    public ResponseEntity<List<FarmerDetails>> getFarmerDetailsByName(@RequestParam(name = "name") String name){
        List<FarmerDetails> farmerDetailsByName = farmerService.getFarmerDetailsByCriteriaApi(name);
        return  ResponseEntity.status(HttpStatus.OK).body(farmerDetailsByName);
    }

    @GetMapping("/api/getFarmerDataByName")
    public ResponseEntity<List<FarmerDto2>> getFarmerDataByName(@RequestParam(name = "name") String name){
        List<FarmerDto2> farmerDetailsByName = farmerService.getFarmerDetailsByDynamicQuery(name);
        return  ResponseEntity.status(HttpStatus.OK).body(farmerDetailsByName);
    }

    @GetMapping("/api/getFarmerDetailsByNameAndPhone")
    public ResponseEntity<List<FarmerDetails>> getFarmerDetailsByNameAndPhone(@RequestParam(name = "name") String name, @RequestParam(name = "phone") String phone){
        List<FarmerDetails> farmerDetailsByNameAndPhone = farmerService.getFarmerDetailsByNameAndPhone(name,phone);

        return  ResponseEntity.status(HttpStatus.OK).body(farmerDetailsByNameAndPhone);
    }


    @GetMapping("/api/getFarmerDetailsByLimit")
    public ResponseEntity<List<FarmerDetails>> getFarmerDetailsByNameAndLimit(@RequestParam(name = "name") String name, @RequestParam(name ="limit") int limit, @RequestParam(name = "pageNumber") int pageNumber){
        List<FarmerDetails> farmerDetailsList = farmerService.getFarmerDetailsWithLimit(name,limit,pageNumber);
        return ResponseEntity.status(HttpStatus.OK).body(farmerDetailsList);
    }


    @GetMapping("/api/getFarmerDetailsByFieldName")
    public ResponseEntity<List<FarmerDto>> getFarmerDetailsByFieldName(@RequestParam(name = "name") String name){
        List<FarmerDto> farmerDetailsByFieldName = farmerService.getFarmerDetailsAsDTO(name);
        return  ResponseEntity.status(HttpStatus.OK).body(farmerDetailsByFieldName);
    }



}
