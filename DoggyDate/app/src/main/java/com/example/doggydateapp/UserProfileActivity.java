package com.example.doggydateapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.sql.SQLException;

public class UserProfileActivity extends Activity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Intent i = getIntent();
        String email = i.getStringExtra("userEmail");

        Button editProfile = (Button) findViewById(R.id.circular_button);

        TextView userNameText = (TextView) findViewById(R.id.userNameText);
        TextView bioText = (TextView) findViewById(R.id.bioText);
        TextView ageText = (TextView) findViewById(R.id.ageText);
        TextView locationText = (TextView) findViewById(R.id.locationText);
        TextView genderText = (TextView) findViewById(R.id.genderText);
        TextView sexualityText = (TextView) findViewById(R.id.sexualityText);
        TextView schoolText = (TextView) findViewById(R.id.schoolText);
        TextView jobText = (TextView) findViewById(R.id.jobText);
        TextView interestsText = (TextView) findViewById(R.id.interestsText);
        ImageView profilePicture = (ImageView) findViewById(R.id.profilePicture);
        int imageWidth = 1132;
        int imageHeight = 1536;

        Users users = pullUserData(email);

        userNameText.setText(users.getName());
        bioText.setText("About Me: " + users.getBio());
        ageText.setText(users.getAge());
        locationText.setText(users.getLocation());
        genderText.setText("Gender: " + users.getGender());
        sexualityText.setText("Sexuality: " + users.getSexuality());
        schoolText.setText(users.getSchool());
        jobText.setText(users.getJob());
        interestsText.setText("Interests: " + users.getInterests());

        Log.i("bytearray", String.valueOf(users.getProfilePicture()));
        byte[] pic = users.getProfilePicture();

        try {
            Bitmap bmp = BitmapFactory.decodeByteArray(pic, 0, pic.length);
            Log.i("bit", String.valueOf(bmp));
            profilePicture.setImageBitmap(bmp);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }



        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserProfileActivity.this, CreateUserActivity.class);
                intent.putExtra("userBio", users.getBio());
                intent.putExtra("userLocation", users.getLocation());
                intent.putExtra("userGender", users.getGender());
                intent.putExtra("userSexuality", users.getSexuality());
                intent.putExtra("userSchool", users.getSchool());
                intent.putExtra("userJob", users.getJob());
                intent.putExtra("userInterests", users.getInterests());
                intent.putExtra("userEmail", email);
                intent.putExtra("userPicture", pic);
                startActivity(intent);
            }

        });
    }

    public Users pullUserData(String email) {
        Users users = null;
        try {
            UserDao userDao = new UserDao();
            users = userDao.retrieveUserData(email);
//            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return users;
        }
        
    }
}
