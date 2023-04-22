package com.example.doggydateapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

public class LoginActivity extends Activity {

    //placeholder names.. change if needed
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView registerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        emailEditText = findViewById(R.id.inputemail);
        passwordEditText = findViewById(R.id.inputpassword);
        loginButton = findViewById(R.id.login_button);
        registerTextView = findViewById(R.id.registerTextView);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code for login button click event listener
                String uname = String.valueOf(emailEditText.getText());
                String pword = String.valueOf(passwordEditText.getText());

                if (uname.trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Enter email", Toast.LENGTH_LONG).show();
                }
                else if (pword.trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Enter password", Toast.LENGTH_LONG).show();
                }
                else
                {
                    UserDao userDao = new UserDao();
                    try {
                        Users user = userDao.findUserByUsernamePassword(uname, pword);

//                        if (!user.getEmail().trim().equals(uname))
//                        {
//                            Toast.makeText(getApplicationContext(), "No users registered under this email", Toast.LENGTH_LONG).show();
//                        }

                        if (!user.getEmail().trim().equals("") && !user.getPassword().trim().equals(""))
                        {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("userEmail", user.getEmail());
                            intent.putExtra("userPW", user.getPassword());
                            startActivity(intent);
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code here for opening registration activity...
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


}