package com.example.doggydateapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CreateDog extends Activity {

    //placeholder names.. change if needed
    private EditText inputDogName;
    private EditText inputDogAge;
    private Spinner dogSizeSpinner;
    private Spinner dogTemperamentSpinner;
    private EditText inputDogBio;
    private EditText inputDogBreed;
    private TextView loginTextView;
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_dog);

        inputDogName = findViewById(R.id.inputDogName);
        inputDogAge = findViewById(R.id.inputDogAge);
        dogSizeSpinner = findViewById(R.id.dogSizeSpinner);
        dogTemperamentSpinner = findViewById(R.id.dogTemperamentSpinner);
        inputDogBio= findViewById(R.id.inputDogBio);
        inputDogBreed= findViewById(R.id.inputDogBreed);
        loginTextView = findViewById(R.id.loginText);
        continueButton = findViewById(R.id.continueButton);


        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code here for opening registration activity...
                Intent intent = new Intent(CreateDog.this, DisplayDog.class);
                startActivity(intent);
            }
        });
    }
}