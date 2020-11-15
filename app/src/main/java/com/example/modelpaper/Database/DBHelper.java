package com.example.modelpaper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.example.modelpaper.Database.UserProfile.Users.TABLE_NAME;
import static com.example.modelpaper.Database.UserProfile.Users.USER_DOB;
import static com.example.modelpaper.Database.UserProfile.Users.USER_GENDER;
import static com.example.modelpaper.Database.UserProfile.Users.USER_ID;
import static com.example.modelpaper.Database.UserProfile.Users.USER_NAME;
import static com.example.modelpaper.Database.UserProfile.Users.USER_PASSWORD;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "database.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    USER_ID + " INTEGER PRIMARY KEY," +
                    USER_NAME + " TEXT," +
                    USER_DOB + " TEXT," +
                    USER_PASSWORD + " TEXT," +
                    USER_GENDER + " TEXT)";


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }
    private static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }
    public boolean addInfo(String name , String DOB , String password , String gender){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, name);
        values.put(USER_DOB, DOB);
        values.put(USER_PASSWORD, password);
        values.put(USER_GENDER, gender);
        long result = db.insert(TABLE_NAME, null, values);
        if(result == 0){
            return false;
        }else{
            return true;
        }

    }
    public boolean updateInfor(String name , String DOB , String password , String gender){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_DOB, DOB);
        values.put(USER_PASSWORD, password);
        values.put(USER_GENDER, gender);
        String selection = USER_NAME+ " LIKE ?";
        String[] selectionArgs = { name };

        int count = db.update(TABLE_NAME,values, selection,selectionArgs);
        if(count == 0){
            return false;
        }else{
            return true;
        }
    }
    public boolean deleteInfo(String name){
        SQLiteDatabase db = getWritableDatabase();
        String selection = USER_NAME + " LIKE ?";
        String[] selectionArgs = { name };
        int count = db.delete(TABLE_NAME, selection, selectionArgs);
        if(count == 0){
            return false;
        }else{
            return true;
        }

    }
    public List readAllInfor(){
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {USER_ID , USER_NAME , USER_DOB , USER_PASSWORD , USER_GENDER};
        String sortOrder =USER_NAME + " ASC";

        Cursor cursor = db.query(TABLE_NAME,projection,null,null,null,null,sortOrder);
        List userList = new ArrayList<>();
        while(cursor.moveToNext()) {
            long name = cursor.getLong(cursor.getColumnIndexOrThrow(USER_NAME));
            userList.add(name);
        }
        cursor.close();
        return userList;
    }
    public List readAllInfor(String name){
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {USER_ID , USER_NAME , USER_DOB , USER_PASSWORD , USER_GENDER};
        String selection = USER_NAME + "like = ?";
        String[] selectionArgs = { name };
        String sortOrder = USER_NAME + " ASC";

        Cursor cursor = db.query(TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
        List userList = new ArrayList<>();
        while(cursor.moveToNext()) {
            String names = cursor.getString(cursor.getColumnIndexOrThrow(USER_NAME));
            String dob = cursor.getString(cursor.getColumnIndexOrThrow(USER_DOB));
            String pass = cursor.getString(cursor.getColumnIndexOrThrow(USER_PASSWORD));
            String gender = cursor.getString(cursor.getColumnIndexOrThrow(USER_GENDER));
            System.out.println(names);
            System.out.println(dob);
            System.out.println(pass);
            System.out.println(gender);
            userList.add(names);
            userList.add(dob);
            userList.add(pass);
            userList.add(gender);

        }
        cursor.close();
        return userList;
    }
}
