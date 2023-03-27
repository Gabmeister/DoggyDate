package com.example.doggydateapp;

import static com.example.doggydateapp.UserDBHelper.KEY_AGE;
import static com.example.doggydateapp.UserDBHelper.KEY_EMAIL;
import static com.example.doggydateapp.UserDBHelper.KEY_GENDER;
import static com.example.doggydateapp.UserDBHelper.KEY_ID;
import static com.example.doggydateapp.UserDBHelper.KEY_LOCATION;
import static com.example.doggydateapp.UserDBHelper.KEY_NAME;
import static com.example.doggydateapp.UserDBHelper.KEY_PASSWORD;
import static com.example.doggydateapp.UserDBHelper.KEY_SEXUALITY;
import static com.example.doggydateapp.UserDBHelper.KEY_USERNAME;
import static com.example.doggydateapp.UserDBHelper.TABLE_USER;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;



public class UserDBManager {

    Context context;
    private UserDBHelper UserHelper;
    private SQLiteDatabase database;

    public UserDBManager (Context context)
    {
        this.context = context;
    }

    public UserDBManager open() throws SQLException {
        UserHelper = new UserDBHelper(context);
        database = UserHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        UserHelper.close();
    }

    void addUser(Users user) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_AGE, user.getAge());
        values.put(KEY_GENDER, user.getGender());
        values.put(KEY_SEXUALITY, user.getSexuality());
        values.put(KEY_LOCATION, user.getLocation());

        database.insert(TABLE_USER, null, values);
    }

    Users getUser(int id) {

        Cursor cursor = database.query(TABLE_USER, new String[] {
                KEY_ID, KEY_NAME, KEY_USERNAME, KEY_PASSWORD, KEY_EMAIL, KEY_AGE, KEY_GENDER, KEY_SEXUALITY, KEY_LOCATION }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Users user = new Users(
                cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getString(4), cursor.getInt(5), cursor.getString(6), cursor.getString(7),
                cursor.getString(8));

        return user;
    }
}

