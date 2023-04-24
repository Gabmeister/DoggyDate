package com.example.doggydateapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class UserProfileActivity extends Activity {

    private Button editProfile;
    private TextView userNameText, ageText, locationText, genderText, sexualityText;
    private ImageView profilePicture;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Button editProfile = (Button) findViewById(R.id.editButton);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                String user = i.getStringExtra("userEmail");
                Intent intent = new Intent(UserProfileActivity.this, CreateUserActivity.class);
                intent.putExtra("userEmail", user);
                startActivity(intent);
            }
        });

    }
}
