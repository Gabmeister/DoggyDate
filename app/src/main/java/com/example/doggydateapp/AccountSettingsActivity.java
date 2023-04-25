package com.example.doggydateapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.MenuItem;
import android.widget.Button;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AccountSettingsActivity extends AppCompatActivity {

    //private Button preferences;
   // private Button editAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_account);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.match:
                        Intent intent = new Intent(AccountSettingsActivity.this, MainActivity.class);
                        startActivity(intent);
                        return true;

                    case R.id.chat:
                        Intent chatIntent = new Intent(AccountSettingsActivity.this, ChatActivity.class);
                        startActivity(chatIntent);
                        return true;

                    case R.id.profile:
                        return true;
                }
                return false;
            }
        });


    }


}