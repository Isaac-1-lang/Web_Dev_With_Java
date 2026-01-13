package com.helloworld.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.helloworld.model.Student;

/**
 * Hibernate Util class is for connection to the Database either Mysql or PostgreSQL
 */

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {

            Configuration configuration = new Configuration();
            Properties settings = getProperties();

            configuration.setProperties(settings);

            // Entity class
            configuration.addAnnotatedClass(Student.class);

            ServiceRegistry serviceRegistry =
                    new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties())
                            .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory;
    }

    private static Properties getProperties() {
        Properties settings = new Properties();

        // PostgreSQL JDBC Driver
        settings.put(Environment.DRIVER, "org.postgresql.Driver");

        // PostgreSQL JDBC URL
        settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/users_servlets");

        settings.put(Environment.USER, "postgres");
        settings.put(Environment.PASS, "121402pr0732021");

        // PostgreSQL Dialect
        settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");

        settings.put(Environment.SHOW_SQL, true);
        settings.put(Environment.HBM2DDL_AUTO, "create-drop");
        return settings;
    }
}
