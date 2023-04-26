package com.example.doggydateapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    ArrayList<Messages> messages = new ArrayList<>();
    int[] contactImages = {R.drawable.img1, R.drawable.img2, R.drawable.img3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        setMessages();

        Intent i = getIntent();
        String email = i.getStringExtra("userEmail");



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_chat);
        bottomNavigationView.setSelectedItemId(R.id.chat);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.match:

                        Intent intent = new Intent(ChatActivity.this, MainActivity.class);
                        intent.putExtra("userEmail", email);
                        startActivity(intent);
                        return true;

                    case R.id.chat:
                        return true;

                    case R.id.profile:


                        Intent profileIntent = new Intent(ChatActivity.this, UserProfileActivity.class);
                        profileIntent.putExtra("userEmail", email);
                        startActivity(profileIntent);
                        return true;
                }
                return false;
            }
        });
    }
    private void setMessages(){
        String[] messagesarray = getResources().getStringArray(R.array.messages);

        for (int i = 0;  i<messagesarray.length ; i++) {
            messages.add(new Messages(messagesarray[i], contactImages[i]));
        }
    }
}