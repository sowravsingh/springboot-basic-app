package com.spring.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CropData {


    @JsonProperty("cn")
    String cropName;
    int cropId;

    @JsonProperty("sn")
    String scientificName;

    public CropData(){

    }


    public CropData(String cropName, int cropId, String scientificName) {
        this.cropName = cropName;
        this.cropId = cropId;
        this.scientificName = scientificName;
    }




    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public int getCropId() {
        return cropId;
    }

    public void setCropId(int cropId) {
        this.cropId = cropId;
    }

    @Override
    public String toString() {
        return "CropData{" +
                "cropName='" + cropName + '\'' +
                ", cropId=" + cropId +
                ", scientificName='" + scientificName + '\'' +
                '}';
    }
}
