package com.helloworld.service;


import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.helloworld.model.StudentModel;
import com.helloworld.util.HibernateUtil;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
// Optional – compiles because hibernate-core adds transaction API
import jakarta.transaction.Transactional;

public class StudentServices {

    private static final SessionFactory sf = HibernateUtil.getSessionFactory();
    private static final StudentServices instance = new StudentServices();

    // Singleton accessor
    public static StudentServices getInstance() {
        return instance;
    }

    public StudentServices() {
    }

    // ---------- CREATE ----------

    public void addStudent(StudentModel student) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.persist(student); // or session.save(student);
            session.getTransaction().commit();
        }
    }

    // ---------- UPDATE ----------

    public void updateStudent(StudentModel student) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.merge(student);
            session.getTransaction().commit();
        }
    }

    // ---------- DELETE ----------

    public void deleteStudent(int id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            StudentModel existing = session.get(StudentModel.class, id);
            if (existing != null) {
                session.remove(existing);
            }
            session.getTransaction().commit();
        }
    }

    // ---------- READ EXAMPLES ----------

    // Just a demo: always returns student with id = 1
    public StudentModel listStudent() {
        try (Session session = sf.openSession()) {
            return session.get(StudentModel.class, 1);
        }
    }

    @Transactional
    public List<StudentModel> getAllData() {
        try (Session session = sf.openSession()) {
            Query<StudentModel> q = session.createQuery("from StudentModel", StudentModel.class);
            return q.getResultList();
        }
    }

    public List<StudentModel> listStudents() {
        try (Session session = sf.openSession()) {
            List<StudentModel> students =
                    session.createQuery("select s from StudentModel s", StudentModel.class)
                            .getResultList();
            Hibernate.initialize(students);
            return students;
        }
    }

    //  Criteria API version
    public List<StudentModel> findAllStudents() {
        try (Session session = sf.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<StudentModel> cq = cb.createQuery(StudentModel.class);
            Root<StudentModel> root = cq.from(StudentModel.class);
            cq.select(root); // SELECT s FROM Student s

            Query<StudentModel> query = session.createQuery(cq);
            return query.getResultList();
        }
    }

    public List<StudentModel> getAll() {
        try (Session session = sf.openSession()) {
            Query<StudentModel> q = session.createQuery("from StudentModel", StudentModel.class);
            return q.getResultList();
        }
    }

    public StudentModel getStudentById(int id) {
        try (Session session = sf.openSession()) {
            StudentModel student = session.get(StudentModel.class, id); // load(...) is LAZY
            Hibernate.initialize(student); // ensure initialized before session closes
            return student;
        }
    }
}