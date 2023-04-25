package com.example.doggydateapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.material.bottomnavigation.BottomNavigationView;
public class MainActivity extends AppCompatActivity {

    private Button likeButton;
    private Button dislikeButton;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        likeButton = findViewById(R.id.like_button);
        dislikeButton = findViewById(R.id.dislike_button);

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // code here for dislike button click/swipe
            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.match);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {

                case R.id.account:
                    Intent i = getIntent();
                    String user = i.getStringExtra("userEmail");
                    Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
                    intent.putExtra("userEmail", user);
                    startActivity(intent);
                    return true;
                case R.id.match:
                    return true;
                case R.id.chat:
                    return true;

            }
            return false;
        });
    }
}