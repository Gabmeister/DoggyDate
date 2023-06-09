package com.example.doggydateapp;


import android.os.StrictMode;

import android.util.Log;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnector {

    private static String ip = "ec2-34-252-169-131.eu-west-1.compute.amazonaws.com";// this is the host ip that your data base exists on you can use 10.0.2.2 for local host                                                    found on your pc. use if config for windows to find the ip if the database exists on                                                    your pc
    private static String port = "5432";// the port sql server runs on
    private static String Classes = "org.postgresql.Driver";// the driver that is required for this connection use                                                                           "org.postgresql.Driver" for connecting to postgresql
    private static String database = "dfktl2jjnuovra"; // the data base name
    private static String username = "gtujllzdpnkgmt";// the user name
    private static String password = "4e7120324baa7dc4744b1b2ab3d49e1ff0fade6c9cee10b4cd9987ce0b760fea";// the password
    private static String url = "jdbc:postgresql://"+ip+":"+port+"/"+database; // the connection url string

    private Connection connection = null;


    public Connection conToDB() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(url, username,password);
            Log.i("dbconn", "connected");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.i("dbconn", "class fail");
        } catch (SQLException e) {
            e.printStackTrace();
            Log.i("dbconn", "not connected");
        }
        return connection;
    }

    public void freeConnection(Connection con) throws SQLException {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            System.out.println("Failed to free connection: " + e.getMessage());
            System.exit(1);
        }
    }
}
