package com.helloworld.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class TeacherModel {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String name;
    @Column(nullable = false,unique = true)
    private String email;
}
