package com.spring.serivces;

import com.spring.Entities.UserAuthEntity;
import com.spring.Repository.UserAuthEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthEntityService implements UserDetailsService {

    @Autowired
    private UserAuthEntityRepository userAuthEntityRepository;


    public UserAuthEntity saveUserDetails(UserAuthEntity userAuthEntity){
        return userAuthEntityRepository.save(userAuthEntity);
    }



    @Override
    public UserAuthEntity loadUserByUsername(String username) throws UsernameNotFoundException {

        UserAuthEntity userAuthEntity = userAuthEntityRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("user name not found"));
        System.out.println(" user entity is "+userAuthEntity);
        return userAuthEntity;
    }
}
