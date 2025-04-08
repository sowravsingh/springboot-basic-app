package com.spring.components;

import org.springframework.stereotype.Component;

@Component
public class Job {
    int id;
    String role;


    public Job() {}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Job(int id, String role) {
        this.id = id;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
