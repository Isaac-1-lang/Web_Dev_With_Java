package com.helloworld.util;



import java.sql.*;

/**
 * Java Database Connectivity version for Hibernate ORM framework
 * @author Isaac-1-lang
 *
 */
public class JDBCExample {
    public static void  main(String[] args) {
        String url = "jdbc:postgredsql://localhost:5432/users_servlets";
        String user = "postgres";
        String password = "121402pr0732021";


        try {
            /**
             * Creation or calling of the drivers which help in connection to the Database
             */
            Class.forName("org.postgresql.Driver");
            // Managing the DB connection
            Connection conn  = DriverManager.getConnection(url,user,password);
            System.out.println(conn.getAutoCommit());
            String sql  = "SELECT * FROM student";
            // Executing Queries to the DB's tables
            PreparedStatement stmt = conn.prepareStatement(sql);
            // Storing and controlling the results which are fetched from the DB's table before being rendered or doing something else on them!!!
            ResultSet rs = stmt.executeQuery();



            while(rs.next()) {
                System.out.println(rs.getInt("id")+" "+rs.getString("name"));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
