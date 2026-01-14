package com.helloworld;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Using JDBC to connect to the postgreSQL DB
 * @author Isaac-1-lang
 * @version 0.0.1
 * @since 14th Jan 2026
 */
public class DatabaseConnection {
   private static final String url  = "jdbc:postgresql://localhost:5432/users_servlets";
   private static final String username= "postgres";
   private static final String password = "121402pr0732021";
   public static Connection getConnection()  {
     try {
      return DriverManager.getConnection(url,username,password);
     } catch(Exception e) {
       throw new RuntimeException("Datase connection failed" + e.getMessage());
     }
   }
  
}