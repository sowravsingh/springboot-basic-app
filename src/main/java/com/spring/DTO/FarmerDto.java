package com.spring.DTO;

public class FarmerDto {

    private String farmerName;


    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    private String fieldName;

    public FarmerDto(String farmerName, String fieldName) {
        this.farmerName = farmerName;
        this.fieldName = fieldName;
    }

    public FarmerDto() {
    }
}
