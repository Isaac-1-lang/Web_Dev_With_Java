package com.helloworld.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class CombinationModel {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Integer maxMarks;
}
