package com.example.doggydateapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                String uname = String.valueOf(emailEditText.getText());
                String pword = String.valueOf(passwordEditText.getText());

                if (uname.trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "enter username", Toast.LENGTH_LONG).show();
                }
                else if (pword.trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "enter password", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "cool", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
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