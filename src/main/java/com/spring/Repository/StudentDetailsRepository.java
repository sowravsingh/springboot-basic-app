package com.spring.Repository;

import com.spring.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDetailsRepository extends JpaRepository<Student ,Long> {
}
