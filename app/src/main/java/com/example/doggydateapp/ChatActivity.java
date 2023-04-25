package com.example.doggydateapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.chat);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {

                case R.id.match:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    return true;
                case R.id.chat:
                    return true;
                case R.id.account:
                    startActivity(new Intent(getApplicationContext(), AccountSettingsActivity.class));
                    return true;
            }

            return false;
        });
    }
}