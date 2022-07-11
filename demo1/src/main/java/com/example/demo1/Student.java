package com.example.demo1;

import java.util.ArrayList;

public class Student {
    private String id;
    private String name;
    private String firstName;
    private String lastName;
    private String active;
    private String grades;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String isActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public Student(String id, String userName, String firstName, String lastName, String active, String grades) {
        this.id = id;
        this.name = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        this.grades = grades;
    }
}
