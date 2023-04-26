package com.example.doggydateapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.LinkedList;
import java.util.Queue;
import com.example.doggydateapp.UserDao;
import com.example.doggydateapp.Users;

import java.sql.SQLException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button likeButton;
    private Button dislikeButton;
    private Queue<Users> usersQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usersQueue = new LinkedList<>();
        Intent i = getIntent();
        String email = i.getStringExtra("userEmail");

        UserDao userDao = new UserDao();
        try {
            ArrayList<Users> allUsers = userDao.retrieveAllUsers(email);
            usersQueue.addAll(allUsers);
        } catch (SQLException e) {
            e.printStackTrace();
        }



        likeButton = findViewById(R.id.like_button);
        dislikeButton = findViewById(R.id.dislike_button);

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //like button code

            }
        });

        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // code here for dislike button click/swipe
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.match);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.match:
                        return true;

                    case R.id.chat:
                        //change placeholder x.class to corresponding page.
                        Intent intent = new Intent(MainActivity.this, ChatActivity.class); //<-- REPLACE HERE
                        intent.putExtra("userEmail", email);
                        startActivity(intent);
                        return true;

                    case R.id.profile:
                        //change placeholder x.class to corresponding page (profile page).

                        Intent account_intent = new Intent(MainActivity.this, UserProfileActivity.class); //<-- REPLACE HERE
                        account_intent.putExtra("userEmail", email);
                        startActivity(account_intent);
                        return true;
                }
                return false;
            }
        });


    }


}