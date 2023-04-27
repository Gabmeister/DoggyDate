package com.example.doggydateapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.IOException;
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

        Users users = pullUserData(email);
        Log.i("user", users.getName());

        userNameText.setText(users.getName());
        bioText.setText(users.getBio());
        ageText.setText(users.getAge());
        locationText.setText(users.getLocation());
        genderText.setText(users.getGender());
        sexualityText.setText(users.getSexuality());
        schoolText.setText(users.getSchool());
        jobText.setText(users.getJob());
        interestsText.setText(users.getInterests());

        Integer rotate = users.getRotate();

        Log.i("bytearray", String.valueOf(users.getProfilePicture()));
        byte[] pic = users.getProfilePicture();

        try {
            Bitmap bmp = BitmapFactory.decodeByteArray(pic, 0, pic.length);

            profilePicture.setImageBitmap(bmp);
            profilePicture.setRotation(rotate);


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
                intent.putExtra("userRotate", rotate);
                finish();
                startActivity(intent);
            }

        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_account);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.match:
                        Intent intent = new Intent(UserProfileActivity.this, MainActivity.class);
                        intent.putExtra("userEmail", email);
                        startActivity(intent);
                        return true;

                    case R.id.chat:
                        Intent chatIntent = new Intent(UserProfileActivity.this, ChatActivity.class);
                        chatIntent.putExtra("userEmail", email);
                        startActivity(chatIntent);
                        return true;

                    case R.id.profile:
                        return true;
                }
                return false;
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

    public static int getCameraPhotoOrientation(String imagePath) {
        int rotate = 0;
        try {
            ExifInterface exif  = null;
            try {
                exif = new ExifInterface(imagePath);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);
            switch (orientation) {

                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;

                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 90;
                    break;
                default:
                    rotate = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rotate;
    }
}
