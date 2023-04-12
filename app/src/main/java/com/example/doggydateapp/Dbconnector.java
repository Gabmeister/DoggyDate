package com.example.doggydateapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.StrictMode;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnector {

    private static String ip = "ec2-52-215-68-14.eu-west-1.compute.amazonaws.com";// this is the host ip that your data base exists on you can use 10.0.2.2 for local host                                                    found on your pc. use if config for windows to find the ip if the database exists on                                                    your pc
    private static String port = "5432";// the port sql server runs on
    private static String Classes = "org.postgresql.Driver";// the driver that is required for this connection use                                                                           "org.postgresql.Driver" for connecting to postgresql
    private static String database = "d139tuiqiqivv8";// the data base name
    private static String username = "kiqqybsxvyoxyr";// the user name
    private static String password = "fa832fbf03569950c08e571c5113e03e97e6a2172c9091ab482cd30360ef20e0";// the password
    private static String url = "jdbc:postgresql://"+ip+":"+port+"/"+database; // the connection url string

    private Connection connection = null;






    public void conToDB() {

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
    }
}
