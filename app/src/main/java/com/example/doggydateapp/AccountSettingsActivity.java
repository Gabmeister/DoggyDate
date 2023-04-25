package com.example.doggydateapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Button;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AccountSettingsActivity extends AppCompatActivity {

    private Button preferences;
    private Button editAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.account);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {

                case R.id.match:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    return true;
                case R.id.chat:
                    startActivity(new Intent(getApplicationContext(), ChatActivity.class));
                    return true;
                case R.id.account:
                    return true;
            }

            return false;
        });
    }


}