package com.example.doggydateapp;



import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class CreateUserActivity extends Activity {

    //placeholder names.. change if needed
    private EditText inputBio;
    private EditText inputGender;
    private EditText inputSexuality;
    private EditText inputLocation;
    private EditText inputJob;
    private EditText inputSchool;
    private Spinner interestSpinner1, interestSpinner2, interestSpinner3;
    private Button uploadPhoto;
    private Button submitButton;
    private ImageView profilePicture;
    private BottomNavigationView bottomNavigationView;
    Uri imageUri;
    private static final int PICK_IMAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        inputBio = findViewById(R.id.inputBio);
        inputGender = findViewById(R.id.inputGender);
        inputSexuality = findViewById(R.id.inputSexuality);
        inputLocation = findViewById(R.id.inputLocation);
        inputJob = findViewById(R.id.inputJob);
        inputSchool = findViewById(R.id.inputSchool);
        interestSpinner1 = findViewById(R.id.interestsSpinner1);
        interestSpinner2 = findViewById(R.id.interestsSpinner2);
        interestSpinner3 = findViewById(R.id.interestsSpinner3);
        submitButton = findViewById(R.id.submitButton);
        uploadPhoto = findViewById(R.id.uploadPhoto);
        profilePicture = findViewById(R.id.profilePic);
        Intent i = getIntent();
        String currentBio = i.getStringExtra("userBio");
        String currentGender = i.getStringExtra("userGender");
        String currentSexuality = i.getStringExtra("userSexuality");
        String currentLocation = i.getStringExtra("userLocation");
        byte[] pic = i.getByteArrayExtra("userPicture");

        try {
            Bitmap bmp = BitmapFactory.decodeByteArray(pic, 0, pic.length);
            profilePicture.setImageBitmap(bmp);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }



        inputBio.setText(currentBio);
        inputGender.setText(currentGender);
        inputSexuality.setText(currentSexuality);
        inputLocation.setText(currentLocation);


        uploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
//        loginTextView = findViewById(R.id.loginText);
//        continueButton = findViewById(R.id.continueButton);
//
//
//        continueButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //code here for opening registration activity...
//                Intent i = getIntent();
//                String user = i.getStringExtra("userEmail");
//                Intent intent = new Intent(CreateUserActivity.this, CreateUserContinued.class);
//                intent.putExtra("userEmail", user);
//                startActivity(intent);
//            }
//        });


//        createButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //code here to handle register button click..
//
//                String age = String.valueOf(inputUser.getText());
//                String gender  = String.valueOf(inputPass.getText());
//                String sexuality = String.valueOf(inputConfirmPass.getText());
//                String location = String.valueOf(inputEmail.getText());
//                String interests = String.valueOf(inputEmail.getText());
//                String bio = String.valueOf(inputEmail.getText());
//                String education = String.valueOf(inputEmail.getText());
//                String job = String.valueOf(inputEmail.getText());
//
//                // Log.i("register", uname+", "+pword+", "+conPWord+", "+email);
//
//                if (age.trim().equals(""))
//                {
//                    Toast.makeText(getApplicationContext(), "Enter age", Toast.LENGTH_LONG).show();
//                }
//                else if (gender.trim().equals(""))
//                {
//                    Toast.makeText(getApplicationContext(), "Enter sexuality", Toast.LENGTH_LONG).show();
//                }
//                else if (sexuality.trim().equals(""))
//                {
//                    Toast.makeText(getApplicationContext(), "Enter location ", Toast.LENGTH_LONG).show();
//                }
//                else if (location.trim().equals(""))
//                {
//                    Toast.makeText(getApplicationContext(), "Enter interests", Toast.LENGTH_LONG).show();
//                }
//                else if (interests.trim().equals(""))
//                {
//                    Toast.makeText(getApplicationContext(), "Enter a biography", Toast.LENGTH_LONG).show();
//                }
//
//                else if (bio.trim().equals(""))
//                {
//                    Toast.makeText(getApplicationContext(), "Enter education", Toast.LENGTH_LONG).show();
//                }
//
//                else if (education.trim().equals(""))
//                {
//                    Toast.makeText(getApplicationContext(), "Enter occupation", Toast.LENGTH_LONG).show();
//                }
//
//                else if (job.trim().equals(""))
//                {
//                    Toast.makeText(getApplicationContext(), "Password and confirm password don't match", Toast.LENGTH_LONG).show();
//                }
//                else
//                {
//
//                    UserDao userDao = new UserDao();
//                    try {
//                        Users checkUser = userDao.findUserByUsernamePassword(email, pword);
//                        //Toast.makeText(getApplicationContext(), checkUser.getEmail(), Toast.LENGTH_LONG).show();
//                        if (checkUser.getEmail().trim().equals(email)) {
//                            Toast.makeText(getApplicationContext(), "A user with this email already exists", Toast.LENGTH_LONG).show();
//                        }
//                        else
//                        {
//                            try {
//                                userDao.registerUser(uname, pword, email);
//
//                            } catch (SQLException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }
//                //finish();
//            }
//      });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.match);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {

                case R.id.account:
                    Intent in = getIntent();
                    String user = in.getStringExtra("userEmail");
                    Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
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

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();

            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(imageUri, filePath,
                    null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            String FilePathStr = c.getString(columnIndex);
            c.close();

            Log.i("imageuri", String.valueOf(imageUri));
            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                Intent i = getIntent();
                String user = i.getStringExtra("userEmail");
                UserDao userDao = new UserDao();
                userDao.uploadUserImage(bitmap, user);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}