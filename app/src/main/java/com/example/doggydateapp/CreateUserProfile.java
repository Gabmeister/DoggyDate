package com.example.doggydateapp;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

public class CreateUserProfile extends Activity {

    //placeholder names.. change if needed
    private EditText inputAge;
    private EditText inputGender;
    private EditText inputSexuality;
    private EditText inputLocation;
    private EditText inputInterests;
    private TextView loginTextView;
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);


//        inputAge = findViewById(R.id.inputAge);
//        inputGender = findViewById(R.id.inputGender);
//        inputSexuality = findViewById(R.id.inputSexuality);
//        inputLocation = findViewById(R.id.inputLocation);
//        inputInterests = findViewById(R.id.inputInterests);
//        loginTextView = findViewById(R.id.loginText);
//        continueButton = findViewById(R.id.continueButton);


//        continueButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //code here for opening registration activity...
//                Intent intent = new Intent(CreateUserProfile.this, CreateUserProfileContinued.class);
//                startActivity(intent);
//            }
//        });



//        loginTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //code here to open login activity...
//                finish();
//            }
//        });
    }
}