package com.helloworld.service;

import java.util.List;

import com.helloworld.model.Student;
import com.helloworld.util.HibernateUtil;
import java.time.LocalDate;
public class Program {

    public static void main(String[] args) {

        // Initialize Hibernate SessionFactory
        HibernateUtil.getSessionFactory();

        // Access the service (Singleton)
        StudentServices service = StudentServices.getInstance();

        // ---------- ADD STUDENTS ----------
        System.out.println("Adding students...");

        service.addStudent(new Student("Alice", "alice@gmail.com", "RCA",LocalDate.of(2009, 1, 1)));
        service.addStudent(new Student("Bob", "bob@gmail.com", "RCA",LocalDate.of(2008, 1, 1)));
        service.addStudent(new Student("Chris", "chris@gmail.com", "KICS",LocalDate.of(2018, 1, 1)));

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