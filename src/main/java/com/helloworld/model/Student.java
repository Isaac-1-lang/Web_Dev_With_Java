package com.helloworld.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {
    @Id
    private int id;
    private String name;
    private String email;
    private String school;
    private int age;
    public Student() {

    }
    /**
     * @param id
     * @param name
     * @param email
     * @param school
     * @param age
     */
    public Student(int id, String name, String email, String school, int age) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.school = school;
        this.age = age;
    }
    /**
     * @param name
     * @param email
     * @param school
     * @param age
     */
    public Student(String name, String email, String school, int age) {
        this.name = name;
        this.email = email;
        this.school = school;
        this.age = age;
    }
    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", email=" + email + ", school=" + school + ", age=" + age + "]";
    }

}