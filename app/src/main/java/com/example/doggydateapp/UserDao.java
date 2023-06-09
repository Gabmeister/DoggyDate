package com.example.doggydateapp;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UserDao extends Dbconnector {


    public Users registerUser(String name, String email, String pword, String age) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Users u = null;
        try {
            con = this.conToDB();

            String query = "INSERT INTO public.\"Users\"(\"UserID\", \"Name\", \"Password\", \"Email\", \"Age\") VALUES (gen_random_uuid(), ?, ?, ?, ?);";
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, pword);
            ps.setString(4, age);
            Log.i("register", String.valueOf(ps));

            rs = ps.executeQuery();
            if (rs.next()) {
                String userId = rs.getString("UserID");
                String uName = rs.getString("Name");
                String password = rs.getString("Password");
                String uEmail = rs.getString("Email");

                u = new Users(userId, uName, password, uEmail, "", "", "", "", "", "", "", "", null, null);
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
                String uEmail = rs.getString("Email");
                String password = rs.getString("Password");

                u = new Users(null, null, password, uEmail, null, null, null, null, null, null, null, null, null, null);
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

    public Users findUserByEmail(String email) throws SQLException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Users u = null;
        try {
            con = this.conToDB();

            String query = "SELECT * FROM public.\"Users\" WHERE \"Email\" = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, email);

            rs = ps.executeQuery();
            if (rs.next()) {
                String uEmail = rs.getString("Email");

                u = new Users(null, null, null, uEmail, null, null, null, null, null, null, null, null, null, null);
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

    public Users retrieveUserData(String email) throws SQLException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Users u = null;
        try {
            con = this.conToDB();

            String query = "SELECT * FROM public.\"Users\" WHERE \"Email\" = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, email);

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
                String bio = rs.getString("Bio");
                String school = rs.getString("School/College");
                String job = rs.getString("Job");
                String interests = rs.getString("Interests");
                byte[] picture = rs.getBytes("Image");
                Integer rotate = rs.getInt("Rotate");

                Log.i("bytearray", String.valueOf(picture));

                u = new Users(userId, name, password, uEmail, age, gender, sexuality, location, bio, school, job, interests, picture, rotate);

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

    public void editUserProfile(String user, String gender, String sexuality, String location, String bio, String education, String job, String interests) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = this.conToDB();

            String query = "UPDATE public.\"Users\" SET \"Gender\" = ?, \"Sexuality\" = ?, \"Location\" = ?, \"Bio\" = ?, \"School/College\" = ?, \"Job\" = ?, \"Interests\" = ? WHERE \"Email\" = ?;";
            ps = con.prepareStatement(query);
            ps.setString(1, gender);
            ps.setString(2, sexuality);
            ps.setString(3, location);
            ps.setString(4, bio);
            ps.setString(5, education);
            ps.setString(6, job);
            ps.setString(7, interests);
            ps.setString(8, user);
            ps.executeQuery();


        } catch (SQLException e) {
            throw new SQLException("editUser " + e.getMessage());
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
                throw new SQLException("editUser" + e.getMessage());
            }
        }

    }

    public void updateDog(String email, String name, String age, String breed, String size, String temperament, String bio) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = this.conToDB();

            String query = "UPDATE public.\"Users\" SET \"dogName\" = ?, \"dogAge\" = ?, \"dogBreed\" = ?, \"dogSize\" = ?, \"dogTemperment\" = ?, \"dogBio\" = ? WHERE \"Email\" = ?;";
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, age);
            ps.setString(3, breed);
            ps.setString(4, size);
            ps.setString(5, temperament);
            ps.setString(6, bio);
            ps.setString(7, email);
            ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
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
                throw new SQLException("editUser" + e.getMessage());
            }
        }
    }

    public void removeDog(String email) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = this.conToDB();
          
            String query = "UPDATE public.\"Users\" SET \"dogName\" = ?, \"dogAge\" = ?, \"dogBreed\" = ?, \"dogSize\" = ?, \"dogTemperment\" = ?, \"dogBio\" = ?, \"dogImage\" = ? WHERE \"Email\" = ?;";
            ps = con.prepareStatement(query);
            ps.setString(1, null);
            ps.setString(2, null);
            ps.setString(3, null);
            ps.setString(4, null);
            ps.setString(5, null);
            ps.setString(6, null);
            ps.setBytes(7, null);
            ps.setString(8, email);
            ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
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
                throw new SQLException("editUser" + e.getMessage());
            }
        }
    }

    public Dog retrieveDog (String email) throws SQLException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Dog d = null;
        try {
            con = this.conToDB();

            String query = "SELECT * FROM public.\"Users\" WHERE \"Email\" = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, email);

            rs = ps.executeQuery();
            if (rs.next()) {
                String breed = rs.getString("dogBreed");
                String name = rs.getString("dogName");
                String age = rs.getString("dogAge");
                String size = rs.getString("dogSize");
                String temperament = rs.getString("dogTemperment");
                String bio = rs.getString("dogBio");
                byte[] picture = rs.getBytes("dogImage");
                Integer rotate = rs.getInt("dogRotate");

                Log.i("bytearray", String.valueOf(picture));

                d = new Dog(name, age, breed, size, temperament, bio, picture, rotate);

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
        return d;     // u may be null
    }

    public void uploadUserImage(Bitmap bmp, String user, Integer rotate) throws SQLException, FileNotFoundException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = this.conToDB();

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG,80,stream);
            byte[] byteArray = stream.toByteArray();
            ByteArrayInputStream byteStream = new ByteArrayInputStream(byteArray);

            String query = "UPDATE public.\"Users\" SET \"Image\" = ?, \"Rotate\" = ? WHERE \"Email\" = ?;";
            ps = con.prepareStatement(query);
            ps.setBinaryStream(1, byteStream, byteArray.length);
            ps.setString(3, user);
            ps.setInt(2, rotate);
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

    public void uploadDogImage(Bitmap bmp, String user, Integer rotate) throws SQLException, FileNotFoundException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = this.conToDB();

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG,80,stream);
            byte[] byteArray = stream.toByteArray();
            ByteArrayInputStream byteStream = new ByteArrayInputStream(byteArray);

            String query = "UPDATE public.\"Users\" SET \"dogImage\" = ?, \"dogRotate\" = ? WHERE \"Email\" = ?;";
            ps = con.prepareStatement(query);
            ps.setBinaryStream(1, byteStream, byteArray.length);
            ps.setString(3, user);
            ps.setInt(2, rotate);
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

    public ArrayList<Users> retrieveAllUsers(String email) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Users> users = new ArrayList<>();


        try {
            con = this.conToDB();

            String query = "SELECT * FROM public.\"Users\" EXCEPT SELECT * FROM public.\"Users\" WHERE \"Email\" = ?;";
            ps = con.prepareStatement(query);
            ps.setString(1, email);

            rs = ps.executeQuery();
            while (rs.next()) {
                String userId = rs.getString("UserID");
                String uEmail = rs.getString("Email");
                String password = rs.getString("Password");
                String name = rs.getString("Name");
                String age = rs.getString("Age");
                String gender = rs.getString("Gender");
                String sexuality = rs.getString("Sexuality");
                String location = rs.getString("Location");
                String bio = rs.getString("Bio");
                String school = rs.getString("School/College");
                String job = rs.getString("Job");
                String interests = rs.getString("Interests");
                byte[] picture = rs.getBytes("Image");
                Integer rotate = rs.getInt("Rotate");



                Users u = new Users(userId, name, password, uEmail, age, gender, sexuality, location, bio, school, job, interests, picture, rotate);
                Log.i("bytearray", String.valueOf(u.getEmail()));
                users.add(u);

            }
            return users;
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

    }

}