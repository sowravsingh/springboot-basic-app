package com.spring.serivces;

import com.spring.Entities.UserAddress;
import com.spring.Entities.UserDetails;
import com.spring.Repository.UserAddressRepository;
import com.spring.Repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public void saveUserDetails(UserAddress userAddress){
        userAddressRepository.save(userAddress);
    }

    public UserDetails getUserDetailsById(long id){
        return userDetailsRepository.findById(id).get();
    }

    public void saveUserDetails(UserDetails userDetails){
        userDetailsRepository.save(userDetails);
    }

    public UserAddress getUserAddressDetailsById(Long id){
        return userAddressRepository.findById(id).get();
    }

}
