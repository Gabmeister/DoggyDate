package com.example.doggydateapp;

import android.app.Activity;
import android.content.Intent;
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


import java.io.IOException;
import java.sql.SQLException;

public class AddDogActivity extends Activity {

    //placeholder names.. change if needed
    private EditText inputDogName;
    private EditText inputDogAge;
    private EditText inputDogSize;
    private EditText inputDogTemperament;
    private EditText inputDogBio;
    private EditText inputDogBreed;
    private Button submitButton, uploadPhoto;
    private ImageView dogPicture;
    Uri imageUri;
    private static final int PICK_IMAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_dog);

        Intent in = getIntent();
        String user = in.getStringExtra("userEmail");

        inputDogName = findViewById(R.id.inputDogName);
        inputDogAge = findViewById(R.id.inputDogAge);
        inputDogSize = findViewById(R.id.inputDogSize);
        inputDogTemperament= findViewById(R.id.inputDogTemperment);
        inputDogBio= findViewById(R.id.inputDogName);
        inputDogBreed= findViewById(R.id.inputBreed);
        submitButton = findViewById(R.id.submitDog);
        uploadPhoto = findViewById(R.id.uploadPhoto2);
        dogPicture = findViewById(R.id.profilePic2);



        uploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
                //Toast.makeText(getApplicationContext(), "Photo will be changed the next time you load your profile", Toast.LENGTH_LONG).show();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code here to handle register button click..

                String name  = String.valueOf(inputDogName.getText());
                String age = String.valueOf(inputDogAge.getText());
                String breed = String.valueOf(inputDogBreed.getText());
                String size = String.valueOf(inputDogSize.getText());
                String temperament = String.valueOf(inputDogTemperament.getText());
                String bio = String.valueOf(inputDogBio.getText());

                UserDao userDao = new UserDao();
                try {
                    userDao.updateDog(user, name, age, breed, size, temperament, bio);
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                    intent.putExtra("userEmail", user);
                    finish();
                    startActivity(intent);
                }

            }
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
                dogPicture.setImageBitmap(bitmap);
                Integer rotateImage = getCameraPhotoOrientation(FilePathStr);

                dogPicture.setRotation(rotateImage);
                UserDao userDao = new UserDao();
                userDao.uploadDogImage(bitmap, user, rotateImage);



            } catch (IOException | SQLException e) {
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

}