package com.helloworld.util;



import java.sql.*;
public class JDBCExample {
    public static void  main(String[] args) {
        String url = "jdbc:postgredsql://localhost:5432/users_servlets";
        String user = "postgres";
        String password = "121402pr0732021";


        try {
            Class.forName("org.postgresql.Driver");

            Connection conn  = DriverManager.getConnection(url,user,password);
            String sql  = "SELECT * FROM student";

            PreparedStatement stmt = conn.prepareStatement(sql);
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
