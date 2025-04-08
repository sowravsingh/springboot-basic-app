package com.spring.Repository;

import com.spring.pojos.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;


@Repository
public class StudentRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createStudentTable(){
        String query ="CREATE TABLE student(id int AUTO_INCREMENT PRIMARY KEY , name VARCHAR(255))";
        jdbcTemplate.execute(query);
    }


    public void insertStudentDetails(String name){
        String query = "insert into student (name) values(?)";
        int update = jdbcTemplate.update(query, name);
        System.out.println("rows updated are "+update);
    }

    public void insertUsingPSS(String name){
        String query= "insert into student (name) values(?)";
        int update = jdbcTemplate.update(query, (PreparedStatement ps) -> {
            ps.setString(1, name);
        });

    }

    public Student getStudentByName(String name){
        String query = "select * from student where name =? limit 1";
        Student student = jdbcTemplate.queryForObject(query, new Object[]{name}, Student.class);
        return student;
    }

    public List<Student> getStudentDetails(){
        String sql ="select * from student";
        return  jdbcTemplate.query(sql,(rs, rowNum) -> {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            return student;

        });
    }


}
