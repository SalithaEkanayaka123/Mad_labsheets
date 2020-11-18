package com.example.data_handling.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.data_handling.Database.User.users.*;


public class DBHandler extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    private static final String CREATE_TABLE =
            "CREATE TABLE " + USER_TABLE_NAME + " (" +
                    USER_ID + " INTEGER PRIMARY KEY," +
                    USER_NAME + " TEXT," +
                    USER_PASSWORD + " TEXT)";

    private static final String DELETE_TABLE =
            "DROP TABLE IF EXISTS " + USER_TABLE_NAME;

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    public boolean UserdetailsAdded(String username , String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, username);
        values.put(USER_PASSWORD, password);
        long newdata = db.insert(USER_TABLE_NAME , null, values);
        if (newdata > 0){
            return true;
        }else{
            return false;
        }
    }
    public boolean UserdetailsUpdated(String username , String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_PASSWORD, password);
        String selection = USER_NAME + " LIKE ?";
        String[] selectionArgs = { username };

        int value = db.update(
                USER_TABLE_NAME,
                values,
                selection,
                selectionArgs);
        if (value > 0){
            return true;
        }else{
            return false;
        }
    }
    public boolean UserdetailsDeleted(String username){
        SQLiteDatabase db = getWritableDatabase();
        String selection = USER_NAME + " LIKE ?";
        String[] selectionArgs = { username };
        int deletedRows = db.delete(USER_TABLE_NAME, selection, selectionArgs);
        if (deletedRows > 0){
            return true;
        }else{
            return false;
        }
    }
    public ArrayList<String> ListUsers(){
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                USER_ID,
                USER_NAME,
                USER_PASSWORD
        };
        String sortOrder =
                USER_NAME + " DESC";

        Cursor cursor = db.query(
                USER_TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        ArrayList<String> users = new ArrayList<>();
        while(cursor.moveToNext()) {
            String usr = cursor.getString(
                    cursor.getColumnIndexOrThrow(USER_NAME));
            users.add(usr);
        }
        cursor.close();
        return users;
    }
    public List finduser (String username , String password){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                USER_ID,
                USER_NAME,
                USER_PASSWORD
        };
        String selection = USER_NAME + " = ? and " + USER_PASSWORD + " = ?";
        String[] selectionArgs = { username , password };
        String sortOrder =
                USER_NAME + " DESC";

        Cursor cursor = db.query(
                USER_TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
        List userdetails = new ArrayList<>();
        while(cursor.moveToNext()) {
            String usrs = cursor.getString(
                    cursor.getColumnIndexOrThrow(USER_NAME));
            String passwords = cursor.getString(
                    cursor.getColumnIndexOrThrow(USER_PASSWORD));
//            int value = 0;
//            if (usrs.contentEquals(username) && passwords.contentEquals(password)){
//                value++;
//            }
            userdetails.add(usrs);
            userdetails.add(passwords);
        }
        cursor.close();
        return userdetails;
    }
}