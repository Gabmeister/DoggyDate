package com.example.doggydateapp;

import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class UserDao extends Dbconnector {


    public Users registerUser(String name, String email, String pword) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Users u = null;
        try {
            con = this.conToDB();

            String query = "INSERT INTO public.\"Users\"(\"UserID\", \"Name\", \"Password\", \"Email\") VALUES (gen_random_uuid(), ?, ?, ?);";
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, pword);

            rs = ps.executeQuery();
            if (rs.next()) {
                String userId = rs.getString("UserID");
                String uName = rs.getString("Name");
                String password = rs.getString("Password");
                String uEmail = rs.getString("Email");

                u = new Users(userId, uName, password, uEmail, "", "", "", "");
            }
        } catch (SQLException e) {
            throw new SQLException("registerUser " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new SQLException("registerUser" + e.getMessage());
            }
        }
        return u;
    }
    public Users findUserByUsernamePassword(String email, String pword) throws SQLException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Users u = null;
        try {
            con = this.conToDB();

            String query = "SELECT * FROM public.\"Users\" WHERE \"Email\" = ? AND \"Password\" = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, pword);

            rs = ps.executeQuery();
            if (rs.next()) {
                String userId = rs.getString("UserID");
                String uEmail = rs.getString("Email");
                String password = rs.getString("Password");
                String name = rs.getString("Name");
                String age = rs.getString("Age");
                String gender = rs.getString("Gender");
                String sexuality = rs.getString("Sexuality");
                String location = rs.getString("Location");

                u = new Users(userId, name, password, uEmail, age, gender, sexuality, location);
            }
        } catch (SQLException e) {
            throw new SQLException("findUserByUsernamePassword " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new SQLException("findUserByUsernamePassword" + e.getMessage());
            }
        }
        return u;     // u may be null
    }

    public void uploadUserImage(String file, String user) throws SQLException, FileNotFoundException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Users u = null;


        try {
            con = this.conToDB();

            FileInputStream fis = new FileInputStream(file);
            String query = "UPDATE public.\"Users\" SET \"profimage1\" = ? WHERE \"Email\" = ?;";
            ps = con.prepareStatement(query);
            ps.setBinaryStream(1, fis, file.length());
            ps.setString(2, user);
            Log.i("fileupload", String.valueOf(ps));
            ps.executeQuery();


        } catch (SQLException e) {
            throw new SQLException("uploadImage " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }

            } catch (SQLException e) {
                throw new SQLException("uploadImage" + e.getMessage());
            }
        }
    }

}