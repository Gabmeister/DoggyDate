package com.example.doggydateapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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



        emailEditText = findViewById(R.id.inputuser);
        passwordEditText = findViewById(R.id.inputpass);
        loginButton = findViewById(R.id.login_button);
        registerTextView = findViewById(R.id.registerTextView);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code for login button click event listener
            }
        });

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code here for opening registration activity...
            }
        });
    }
}