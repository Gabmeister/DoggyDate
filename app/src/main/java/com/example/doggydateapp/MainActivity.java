package com.example.doggydateapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    private static class DecodeImageTask extends AsyncTask<byte[], Void, Bitmap> {

        private ImageView imageView;

        public DecodeImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(byte[]... bytes) {
            try {
                byte[] data = bytes[0];
                return BitmapFactory.decodeByteArray(data, 0, data.length);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView nameTextView = findViewById(R.id.name_text_view);
        TextView ageTextView = findViewById(R.id.age_text_view);
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

        //Code to display first user in queue upon launch
        Users currentUser = usersQueue.poll();
        ImageView userImage = findViewById(R.id.profile_image);
        try {
            userImage.setImageBitmap(BitmapFactory.decodeByteArray(currentUser.getProfilePicture(), 0, currentUser.getProfilePicture().length));
            nameTextView.setText(currentUser.getName());
            ageTextView.setText(currentUser.getAge());
        } catch (NullPointerException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "No users found for display", Toast.LENGTH_LONG).show();
        }

        likeButton = findViewById(R.id.like_button);
        dislikeButton = findViewById(R.id.dislike_button);

        //click listeners for like/dislike buttons.
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users currentUser = usersQueue.poll(); //retrieve the next user from the queue
                Toast.makeText(MainActivity.this, "Like sent!", Toast.LENGTH_SHORT).show(); //success toast

                if (currentUser != null) {
                    ImageView userImage = findViewById(R.id.profile_image); //update the ImageView to display the user image
                    try {
                        new DecodeImageTask(userImage).execute(currentUser.getProfilePicture());
                        nameTextView.setText(currentUser.getName()); //update name to new user in queue
                        ageTextView.setText(currentUser.getAge());
                        usersQueue.offer(currentUser); //add the user to the end of the queue
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "No more users found", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users currentUser = usersQueue.poll(); //retrieve the next user from the queue
                Toast.makeText(MainActivity.this, "Not Interested..", Toast.LENGTH_SHORT).show();

                if (currentUser != null) {
                    ImageView userImage = findViewById(R.id.profile_image); //update the ImageView to display the user image
                    try {
                        new DecodeImageTask(userImage).execute(currentUser.getProfilePicture());
                        nameTextView.setText(currentUser.getName()); //update name to new user in queue
                        ageTextView.setText(currentUser.getAge());
                        usersQueue.offer(currentUser); //add the user to the end of the queue
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "No more users found", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        //code for bottom navigation bar
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
                        Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                        intent.putExtra("userEmail", email);
                        startActivity(intent);
                        return true;

                    case R.id.profile:
                        //change placeholder x.class to corresponding page (profile page).

                        Intent account_intent = new Intent(MainActivity.this, UserProfileActivity.class);
                        account_intent.putExtra("userEmail", email);
                        startActivity(account_intent);
                        return true;
                }
                return false;
            }
        });


    }


}