package com.spring.Entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.spring.DTO.UserDetailsDTO;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.engine.profile.Fetch;

import javax.persistence.*;

@Table(name = "user_details")
@Entity
@JsonIdentityInfo(property = "id",generator = ObjectIdGenerators.PropertyGenerator.class)
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNum;

    @OneToOne(cascade = CascadeType.ALL)
    private UserAddress userAddress;

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public UserDetails(String name, String phoneNum, UserAddress userAddress) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.userAddress = userAddress;
    }

    public UserDetails() {
    }

    public UserDetailsDTO toUserDetailDto(){
        return new UserDetailsDTO(this);
    }

//    @Override
//    public String toString() {
//        return "UserDetails{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", phoneNum='" + phoneNum + '\'' +
//                ", userAddress=" + userAddress +
//                '}';
//    }
}
