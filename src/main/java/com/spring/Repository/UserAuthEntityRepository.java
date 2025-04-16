package com.spring.Repository;


import com.spring.Entities.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthEntityRepository extends JpaRepository<UserAuthEntity,Long> {

     Optional<UserAuthEntity> findByUserName(String userName);
}
