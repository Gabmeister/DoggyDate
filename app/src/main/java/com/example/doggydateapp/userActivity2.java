package com.example.doggydateapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;

public class userActivity2 extends AppCompatActivity {

    //placeholder names.. change if needed
    private EditText inputBio;
    private EditText inputEducation;
    private EditText inputJob;
    private TextView loginTextView;
    private Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user2);
        inputBio = findViewById(R.id.inputBio);
        inputJob = findViewById(R.id.inputJob);
        inputEducation = findViewById(R.id.inputEducation);
        loginTextView = findViewById(R.id.loginText);
        createButton = findViewById(R.id.createButton);


//        registerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //code here to handle register button click..
//
//                String age = String.valueOf(inputUser.getText());
//                String gender = String.valueOf(inputPass.getText());
//                String sexuality = String.valueOf(inputConfirmPass.getText());
//                String location = String.valueOf(inputEmail.getText());
//                String interests = String.valueOf(inputEmail.getText());
//                String bio = String.valueOf(inputEmail.getText());
//                String education = String.valueOf(inputEmail.getText());
//                String job = String.valueOf(inputEmail.getText());
//
//                // Log.i("register", uname+", "+pword+", "+conPWord+", "+email);
//
//                if (age.trim().equals("")) {
//                    Toast.makeText(getApplicationContext(), "Enter age", Toast.LENGTH_LONG).show();
//                } else if (gender.trim().equals("")) {
//                    Toast.makeText(getApplicationContext(), "Enter sexuality", Toast.LENGTH_LONG).show();
//                } else if (sexuality.trim().equals("")) {
//                    Toast.makeText(getApplicationContext(), "Enter location ", Toast.LENGTH_LONG).show();
//                } else if (location.trim().equals("")) {
//                    Toast.makeText(getApplicationContext(), "Enter interests", Toast.LENGTH_LONG).show();
//                } else if (interests.trim().equals("")) {
//                    Toast.makeText(getApplicationContext(), "Enter a biography", Toast.LENGTH_LONG).show();
//                } else if (bio.trim().equals("")) {
//                    Toast.makeText(getApplicationContext(), "Enter education", Toast.LENGTH_LONG).show();
//                } else if (education.trim().equals("")) {
//                    Toast.makeText(getApplicationContext(), "Enter occupation", Toast.LENGTH_LONG).show();
//                } else if (job.trim().equals("")) {
//                    Toast.makeText(getApplicationContext(), "Password and confirm password don't match", Toast.LENGTH_LONG).show();
//                }
//                else
//                {
//
//                    UserDao userDao = new UserDao();
//                    try {
//                        Users checkUser = userDao.findUserByUsernamePassword(email, pword);
//                        //Toast.makeText(getApplicationContext(), checkUser.getEmail(), Toast.LENGTH_LONG).show();
//                        if (checkUser.getEmail().trim().equals(email)) {
//                            Toast.makeText(getApplicationContext(), "A user with this email already exists", Toast.LENGTH_LONG).show();
//                        }
//                        else
//                        {
//                            try {
//                                userDao.registerUser(uname, pword, email);
//
//                            } catch (SQLException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }
//                //finish();
//            }
//        });


                loginTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //code here to open login activity...
                        finish();
                    }
                });
            }
        }