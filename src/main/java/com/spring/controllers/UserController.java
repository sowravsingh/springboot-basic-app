package com.spring.controllers;

import com.spring.DTO.UserDetailsDTO;
import com.spring.Entities.UserAddress;
import com.spring.Entities.UserAuthEntity;
import com.spring.Entities.UserDetails;
import com.spring.serivces.UserAuthEntityService;
import com.spring.serivces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserAuthEntityService userAuthEntityService;

    @PostMapping("/api/saveAddress")
    public ResponseEntity<String> saveAddressDetails(@RequestBody UserAddress userAddress){
        System.out.println(userAddress);
        userService.saveUserDetails(userAddress);

        return ResponseEntity.status(HttpStatus.OK).body("address inserted successfully");
    }


    @PostMapping("/api/saveUserDetails")
    public ResponseEntity<String> saveUserDetails(@RequestBody  UserDetails userDetails){
        userService.saveUserDetails(userDetails);

        return ResponseEntity.status(HttpStatus.OK).body("user details inserted successfully");
    }


    @GetMapping("/api/getUserById")
    public ResponseEntity<UserDetailsDTO> getUSerDetailsById(@RequestParam (name = "id") Long id){
        UserDetailsDTO userDetailDto = userService.getUserDetailsById(id).toUserDetailDto();
        return ResponseEntity.status(HttpStatus.OK).body(userDetailDto);
    }


    @GetMapping("/api/getUserAddressById")
    public ResponseEntity<UserAddress> getUserAddressById(@RequestParam (name = "id") Long id){
        UserAddress userAddressDetailsById = userService.getUserAddressDetailsById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userAddressDetailsById);
    }


    @PostMapping("/api/registerUser")
    public ResponseEntity<UserAuthEntity> registerUser(@RequestBody UserAuthEntity userAuthEntity){
        userAuthEntity.setPassword(passwordEncoder.encode(userAuthEntity.getPassword()));
        return ResponseEntity.ok(userAuthEntityService.saveUserDetails(userAuthEntity));
    }

}
