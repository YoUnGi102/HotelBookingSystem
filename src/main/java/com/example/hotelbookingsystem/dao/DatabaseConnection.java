package com.example.hotelbookingsystem.dao;

import java.sql.*;

public class DatabaseConnection {

    private static final String USERNAME = "cgvfnzab";
    private static final String PASSWORD = "9tlfavFzzVjEzdgOQrSG-gRxqj-SDNLJ";
    private static final String DATABASE = "cgvfnzab";
    private static final String HOST = "abul.db.elephantsql.com";
    private static final String URL = "jdbc:postgresql://"+HOST+"/"+DATABASE;
    public static final String SCHEMA = "hotel_booking_system";

    private static DatabaseConnection instance;

    private DatabaseConnection(){
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    public static synchronized DatabaseConnection getInstance() {
        if(instance == null){
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }


//    public void connect(String[] args) {
//
//        try {
//            Statement st = connection.createStatement();
//            ResultSet rs = st.executeQuery("SELECT * FROM movie");
//            while (rs.next()) {
//                System.out.print("Column 1 returned ");
//                System.out.println(rs.getString(2));
//                System.out.print("Column 2 returned ");
//                System.out.println(rs.getString(3));
//            }
//            rs.close();
//            st.close();
//        }
//        catch (java.sql.SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }

}
