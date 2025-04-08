package com.spring.DTO;

public class FarmerDto2 {

    private String name;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public FarmerDto2(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
