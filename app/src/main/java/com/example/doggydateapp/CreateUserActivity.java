package com.example.doggydateapp;



import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
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
        String[] noSelection = getResources().getStringArray(R.array.interests);


        Intent i = getIntent();
        String currentBio = i.getStringExtra("userBio");
        String currentGender = i.getStringExtra("userGender");
        String currentSexuality = i.getStringExtra("userSexuality");
        String currentLocation = i.getStringExtra("userLocation");
        String currentJob = i.getStringExtra("userJob");
        String currentSchool = i.getStringExtra("userSchool");
        String currInterests = i.getStringExtra("userInterests");
        String currentInterests = checkCurrentInterests(currInterests);
        byte[] pic = i.getByteArrayExtra("userPicture");
        Integer rotate = i.getIntExtra("userRotate", 0);

        try {
            Bitmap bmp = BitmapFactory.decodeByteArray(pic, 0, pic.length);
            profilePicture.setImageBitmap(bmp);
            profilePicture.setRotation(rotate);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }



        inputBio.setText(currentBio);
        inputGender.setText(currentGender);
        inputSexuality.setText(currentSexuality);
        inputLocation.setText(currentLocation);
        inputJob.setText(currentJob);
        inputSchool.setText(currentSchool);
        String[] splitInterests = currentInterests.split(",");


        for (int x = 0; x < noSelection.length; x++) {
            if (splitInterests[0].trim().equalsIgnoreCase(noSelection[x].trim())) {
                interestSpinner1.setSelection(x);
            }
            if (splitInterests[1].trim().equalsIgnoreCase(noSelection[x].trim())) {
                interestSpinner2.setSelection(x);
            }
            if (splitInterests[2].trim().equalsIgnoreCase(noSelection[x].trim())) {
                interestSpinner3.setSelection(x);
            }
        }





        uploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
                //Toast.makeText(getApplicationContext(), "Photo will be changed the next time you load your profile", Toast.LENGTH_LONG).show();
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



                if (interest1.equalsIgnoreCase(interest2) || interest1.equalsIgnoreCase(interest3) || interest2.equalsIgnoreCase(interest3))
                {
                    Toast.makeText(getApplicationContext(), "You cannot have 2 of the same interests", Toast.LENGTH_LONG).show();
                }
                else
                {
                    if (interest1.contains("Select") || interest2.contains("Select") || interest3.contains("Select"))
                    {
                        Toast.makeText(getApplicationContext(), "You must select 3 interests", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        interests.append(interest1).append(", ").append(interest2).append(", ").append(interest3);

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

            }
      });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {

                case R.id.profile:
                case R.id.match:
                    finish();
//                    Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
//                    intent.putExtra("userEmail", user);
//                    startActivity(intent);
                    return true;
//                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
//                    intent1.putExtra("userEmail", user);
//                    startActivity(intent1);
                case R.id.chat:
                    Intent chatIntent = new Intent(getApplicationContext(), ChatActivity.class);
                    chatIntent.putExtra("userEmail", user);
                    finish();
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

                //Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                Bitmap bitmap = decodeSampledBitmap(FilePathStr, 1280, 960);


                Intent i = getIntent();
                String user = i.getStringExtra("userEmail");
                profilePicture.setImageBitmap(bitmap);
                Integer rotateImage = getCameraPhotoOrientation(FilePathStr);

                profilePicture.setRotation(rotateImage);
                UserDao userDao = new UserDao();
                userDao.uploadUserImage(bitmap, user, rotateImage);



            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static Bitmap decodeSampledBitmap(String res,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(res, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(res, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static int getCameraPhotoOrientation(String imagePath) {
        int rotate = 0;
        try {
            ExifInterface exif  = null;
            try {
                exif = new ExifInterface(imagePath);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);
            switch (orientation) {

                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;

                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                default:
                    rotate = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rotate;
    }

    public String checkCurrentInterests(String interests)
    {
        if(interests != null)
        {
            return interests;
        }
        else
        {
            return "--Select an item--, --Select an item--, --Select an item--";
        }
    }
}