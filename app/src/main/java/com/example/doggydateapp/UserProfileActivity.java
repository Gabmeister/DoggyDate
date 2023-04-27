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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.w3c.dom.Text;

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
        Button addDog = (Button) findViewById(R.id.addDog);
        Button clearDog = (Button) findViewById(R.id.removeDog);

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

        TextView dogNameText = (TextView) findViewById(R.id.dogNameText);
        TextView dogAgeText = (TextView) findViewById(R.id.dogAgeText);
        TextView dogBioText = (TextView)  findViewById(R.id.dogBio);
        TextView dogBreedText = (TextView) findViewById(R.id.dogBreed);
        TextView dogSizeText = (TextView) findViewById(R.id.dogSize);
        TextView dogTemperament = (TextView) findViewById(R.id.dogTemperament);
        ImageView dogProfilePicture = (ImageView) findViewById(R.id.dogProfilePicture);

        Users users = pullUserData(email);
        Dog dog = pullDogData(email);
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

        dogNameText.setText(dog.getName());
        dogAgeText.setText(dog.getAge());
        dogBioText.setText(dog.getBio());
        dogBreedText.setText(dog.getBreed());
        dogSizeText.setText(dog.getSize());
        dogTemperament.setText(dog.getTemperament());
        Integer dogRotate = dog.getRotate();

        Integer rotate = users.getRotate();

        Log.i("bytearray", String.valueOf(users.getProfilePicture()));
        byte[] pic = users.getProfilePicture();
        byte[] dogPic = dog.getPicture();

        try {
            Bitmap bmp = BitmapFactory.decodeByteArray(pic, 0, pic.length);
            profilePicture.setImageBitmap(bmp);
            profilePicture.setRotation(rotate);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        try {
            Bitmap bmp = BitmapFactory.decodeByteArray(dogPic, 0, dogPic.length);
            dogProfilePicture.setImageBitmap(bmp);
            dogProfilePicture.setRotation(dogRotate);
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

        addDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addDog = new Intent(UserProfileActivity.this, AddDogActivity.class);
                addDog.putExtra("userEmail", email);
                startActivity(addDog);
            }
        });

        clearDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    UserDao userDao = new UserDao();
                    userDao.removeDog(email);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "Dog data has been removed", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_account);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.match:
                        finish();
//                        Intent intent = new Intent(UserProfileActivity.this, MainActivity.class);
//                        intent.putExtra("userEmail", email);
//                        startActivity(intent);
                        return true;

                    case R.id.chat:
                        Intent chatIntent = new Intent(UserProfileActivity.this, ChatActivity.class);
                        chatIntent.putExtra("userEmail", email);
                        finish();
                        startActivity(chatIntent);
                        return true;

                    case R.id.profile:
                        return true;
                }
                return false;
            }
        });
    }

    private Dog pullDogData(String email) {
        Dog dog = null;
        try {
            UserDao userDao = new UserDao();
            dog = userDao.retrieveDog(email);
//            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return dog;
        }

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
