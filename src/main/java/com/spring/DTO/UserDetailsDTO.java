package com.spring.DTO;

import com.spring.Entities.UserDetails;

import java.lang.ref.PhantomReference;

public class UserDetailsDTO {

    public Long id;
    private String name;
    private String phoneNo;
    private String city;

    public UserDetailsDTO() {
    }

    public UserDetailsDTO(UserDetails userDetails) {
        this.id = userDetails.getId();
        this.name = userDetails.getName();
        this.phoneNo = userDetails.getPhoneNum();
        this.city = userDetails.getUserAddress() !=null ? userDetails.getUserAddress().getState():null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
