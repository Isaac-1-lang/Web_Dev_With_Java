package com.helloworld.service;

import java.util.List;

import com.helloworld.model.Student;
import com.helloworld.util.HibernateUtil;

public class Program {

    public static void main(String[] args) {

        // Initialize Hibernate SessionFactory
        HibernateUtil.getSessionFactory();

        // Access the service (Singleton)
        StudentServices service = StudentServices.getInstance();

        // ---------- ADD STUDENTS ----------
        System.out.println("Adding students...");

        service.addStudent(new Student("Alice", "alice@gmail.com", "RCA", 18));
        service.addStudent(new Student("Bob", "bob@gmail.com", "RCA", 19));
        service.addStudent(new Student("Chris", "chris@gmail.com", "KICS", 20));

        System.out.println("Students added successfully.");

        // ---------- GET ALL STUDENTS ----------
        System.out.println("\nFetching all students (HQL)...");
        List<Student> allStudents = service.getAll();
        allStudents.forEach(System.out::println);

        // ---------- GET BY ID ----------
        System.out.println("\nFetching student with ID = 1...");
        Student s1 = service.getStudentById(1);
        System.out.println(s1 != null ? s1 : "Student not found");

        // ---------- USING Criteria API ----------
        System.out.println("\nFetching all students using Criteria API...");
        List<Student> critStudents = service.findAllStudents();
        critStudents.forEach(System.out::println);

        // ---------- Fetch using custom method ----------
        System.out.println("\nListStudents()...");
        List<Student> ls = service.listStudents();
        ls.forEach(System.out::println);

        // Close Hibernate
        HibernateUtil.getSessionFactory().close();

        System.out.println("\nProgram finished.");
    }
}