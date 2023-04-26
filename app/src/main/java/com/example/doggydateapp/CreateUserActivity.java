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

        Intent in = getIntent();
        String user = in.getStringExtra("userEmail");

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
        String currentJob = i.getStringExtra("userJob");
        String currentSchool = i.getStringExtra("userSchool");
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
        inputJob.setText(currentJob);
        inputSchool.setText(currentSchool);


        uploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
                Toast.makeText(getApplicationContext(), "Photo will be changed the next time you load your profile", Toast.LENGTH_LONG).show();
            }
        });

        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code here to handle register button click..

                String gender  = String.valueOf(inputGender.getText());
                String sexuality = String.valueOf(inputSexuality.getText());
                String location = String.valueOf(inputLocation.getText());
                String bio = String.valueOf(inputBio.getText());
                String education = String.valueOf(inputSchool.getText());
                String job = String.valueOf(inputJob.getText());
                String interest1 = String.valueOf(interestSpinner1.getSelectedItem());
                String interest2 = String.valueOf(interestSpinner2.getSelectedItem());
                String interest3 = String.valueOf(interestSpinner3.getSelectedItem());
                StringBuilder interests = new StringBuilder();
                String[] noSelection = getResources().getStringArray(R.array.interests);
                Log.i("interestsstr", interest1);
                Log.i("intereststtt", noSelection[0]);



                if (interest1.equalsIgnoreCase(interest2) || interest1.equalsIgnoreCase(interest3) || interest2.equalsIgnoreCase(interest3))
                {
                    Toast.makeText(getApplicationContext(), "You cannot have 2 of the same interests", Toast.LENGTH_LONG).show();
                }
                else
                {
                    if (interest1.contains("Select"))
                    {

                    } else {
                        interests.append(interest1).append(", ");
                    }
                    if (interest2.contains("Select"))
                    {

                    } else {
                        interests.append(interest2).append(", ");
                    }
                    if (interest3.contains("Select"))
                    {

                    } else {
                        interests.append(interest3);
                    }


                    UserDao userDao = new UserDao();

                    try {
                        userDao.editUserProfile(user, gender, sexuality, location, bio, education, job, interests.toString());

                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                        intent.putExtra("userEmail", user);
                        finish();
                        startActivity(intent);
                    }
                }

            }
      });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {

                case R.id.profile:

                    Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                    intent.putExtra("userEmail", user);
                    startActivity(intent);
                    return true;
                case R.id.match:
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    intent1.putExtra("userEmail", user);
                    startActivity(intent1);
                    return true;
                case R.id.chat:
                    Intent chatIntent = new Intent(getApplicationContext(), ChatActivity.class);
                    chatIntent.putExtra("userEmail", user);
                    startActivity(chatIntent);
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