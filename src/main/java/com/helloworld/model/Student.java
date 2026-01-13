package com.helloworld.model;

import jakarta.persistence.*;

/**
 * A class which helps to create a model for the data for a student in the database table.
 */
@Entity
public class Student {
    /**
     * @param id
     * @param name
     * @param email
     * @param school
     * @param age
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fName", nullable = false, length = 100)
    private String name;
    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;
    private String school;
    private int age;
    public Student() {

    }

    public Student(int id, String name, String email, String school, int age) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.school = school;
        this.age = age;
    }
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