package com.helloworld.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;

/**
 * A class which helps to create a model for the data for a student in the database table.
 */
@Entity
@Table (name = "students")
public class StudentModel {
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
  @Column(name = "name", nullable = false, length = 100)
  private String name;
  @Column(name = "email", unique = true, nullable = false, length = 100)
  private String email;
  @Column(name="school",nullable = false,length = 100)
  private String school;
  @Transient
  private int age;
  @Column(name = "dob",nullable = false)
  private LocalDate dob;
  public StudentModel() {

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSchool() {
    return school;
  }

  public void setSchool(String school) {
    this.school = school;
  }

  public LocalDate getDob() {
    return dob;
  }

  public void setDob(LocalDate dob) {
    this.dob = dob;
  }

  public StudentModel(String name, String email, String school, LocalDate dob) {
    super();
    this.name = name;
    this.email = email;
    this.school = school;
    this.dob = dob;

  }
  @Override
  public String toString() {
    return "Student [id=" + id + ", name=" + name + ", email=" + email + ", school=" + school + ", age=" + getAge() + "]";
  }
  @Transient
  public int getAge() {
    if(dob == null){
      return 0;
    }
    return Period.between(dob,LocalDate.now()).getYears();
  }
}