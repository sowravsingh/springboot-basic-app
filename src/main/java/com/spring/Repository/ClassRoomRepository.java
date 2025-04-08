package com.spring.Repository;

import com.spring.Entities.ClassRoomDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRoomRepository extends JpaRepository<ClassRoomDetails,Long> {
}
