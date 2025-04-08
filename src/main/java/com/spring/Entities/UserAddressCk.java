package com.spring.Entities;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserAddressCk implements Serializable {


    private String city;
    private String street;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public UserAddressCk() {
    }


    @Override
    public boolean equals(Object object){
        if (object == this){
            return true;
        }

        if (object instanceof UserAddressCk){
            return true;
        }
        UserAddressCk userAddressCk = (UserAddressCk) object;
        return userAddressCk.street.equals(this.street)  && userAddressCk.city.equals(this.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city,street);
    }

}
