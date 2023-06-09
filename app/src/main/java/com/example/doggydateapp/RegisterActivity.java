package com.example.doggydateapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
//import android.widget.Button;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;

public class RegisterActivity extends AppCompatActivity {

    //placeholder names.. change if needed
    private EditText inputUser;
    private EditText inputEmail;
    private EditText inputPass;
    private EditText inputConfirmPass;
    private EditText inputAge;
    private Button registerButton;
    private TextView loginTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        inputUser = findViewById(R.id.inputname);
        inputEmail = findViewById(R.id.inputemail);
        inputPass = findViewById(R.id.inputPassword);
        inputConfirmPass = findViewById(R.id.insertConfirmPassword);
        registerButton = findViewById(R.id.buttonRegister);
        loginTextView = findViewById(R.id.loginText);
        inputAge = findViewById(R.id.inputAge);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code here to handle register button click..

                String uname = String.valueOf(inputUser.getText());
                String pword = String.valueOf(inputPass.getText());
                String conPWord = String.valueOf(inputConfirmPass.getText());
                String email = String.valueOf(inputEmail.getText());
                String age = String.valueOf(inputAge.getText());

                Log.i("register", uname+", "+pword+", "+conPWord+", "+email);

                if (uname.trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Enter username", Toast.LENGTH_LONG).show();
                }
                else if (email.trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Enter email", Toast.LENGTH_LONG).show();
                }
                else if (!email.trim().contains("@"))
                {
                    Toast.makeText(getApplicationContext(), "Invalid email", Toast.LENGTH_LONG).show();
                }
                else if (pword.trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Enter password", Toast.LENGTH_LONG).show();
                }
                else if (conPWord.trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Confirm password", Toast.LENGTH_LONG).show();
                }
                else if (age.trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Enter Age", Toast.LENGTH_LONG).show();
                }
                else if (Integer.valueOf(age) < 21)
                {
                    Toast.makeText(getApplicationContext(), "You must be 21+ to use this app", Toast.LENGTH_LONG).show();
                }

                else if (!pword.trim().equals(conPWord.trim()))
                {
                    Toast.makeText(getApplicationContext(), "Password and confirm password don't match", Toast.LENGTH_LONG).show();
                }
                else
                {

                    UserDao userDao = new UserDao();

                    if (checkUser(email) == true)
                    {
                        Toast.makeText(getApplicationContext(), "A user with this email already exists", Toast.LENGTH_LONG).show();
                    }
                    else {
                        try {
                            userDao.registerUser(uname, pword, email, age);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }

                }

            }
        });


        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code here to open login activity...
                finish();
            }
        });
    }

    public boolean checkUser(String email) {
        try {
            UserDao userDao = new UserDao();
            Users checkUser = userDao.findUserByEmail(email);
            if (checkUser.getEmail().trim().equals(email)) {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }
}