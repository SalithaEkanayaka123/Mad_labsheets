package com.example.labsheetsample_2.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.labsheetsample_2.database.Message_Table.Message.MESSAGE_ID;
import static com.example.labsheetsample_2.database.Message_Table.Message.MESSAGE_MESSAGE;
import static com.example.labsheetsample_2.database.Message_Table.Message.MESSAGE_SUBJECT;
import static com.example.labsheetsample_2.database.Message_Table.Message.MESSAGE_TABLE_NAME;
import static com.example.labsheetsample_2.database.Message_Table.Message.MESSAGE_USER_NAME;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "salitha.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    private static final String CREATE_TABLE =
            "CREATE TABLE " + User_Table.User.USER_TABLE_NAME + " (" +
                    User_Table.User.USER_ID + " INTEGER PRIMARY KEY," +
                    User_Table.User.USER_NAME + " TEXT," +
                    User_Table.User.USER_PASSWORD + " TEXT," +
                    User_Table.User.USER_TYPE + " TEXT)";

    private static final String DELETE_TABLE =
            "DROP TABLE IF EXISTS " + User_Table.User.USER_TABLE_NAME;
    private static final String CREATE_TABLE_1 =
            "CREATE TABLE " + MESSAGE_TABLE_NAME + " (" +
                    MESSAGE_ID + " INTEGER PRIMARY KEY," +
                    MESSAGE_USER_NAME + " TEXT," +
                    MESSAGE_SUBJECT + " TEXT," +
                    MESSAGE_MESSAGE + " TEXT)";

    private static final String DELETE_TABLE_1 =
            "DROP TABLE IF EXISTS " + MESSAGE_TABLE_NAME;

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_1);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        db.execSQL(DELETE_TABLE_1);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    public long insertUsers(String name , String password , String type){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User_Table.User.USER_NAME , name);
        values.put(User_Table.User.USER_PASSWORD , password);
        values.put(User_Table.User.USER_TYPE , type);
        long result = db.insert(User_Table.User.USER_TABLE_NAME , null , values);
        return result;
    }
    public List listUsers(String username , String password){
        SQLiteDatabase db = getReadableDatabase();
        String[] User_Values = {
                User_Table.User.USER_ID,
                User_Table.User.USER_NAME,
                User_Table.User.USER_PASSWORD ,
                User_Table.User.USER_TYPE
        };
        String selection = User_Table.User.USER_NAME + " = ? AND "+ User_Table.User.USER_PASSWORD + " = ? ";
        String[] selectionArgs = { username, password };
        String UserSort =
                User_Table.User.USER_ID + " DESC";

        Cursor cursor = db.query(
                User_Table.User.USER_TABLE_NAME,
                User_Values,
                selection,
                selectionArgs,
                null,
                null,
                UserSort
        );

        ArrayList login = new ArrayList<>();
        while(cursor.moveToNext()) {
            String user = cursor.getString(cursor.getColumnIndexOrThrow(User_Table.User.USER_NAME));
            String pwd_user = cursor.getString(cursor.getColumnIndexOrThrow(User_Table.User.USER_PASSWORD));
            String type_user = cursor.getString(cursor.getColumnIndexOrThrow(User_Table.User.USER_TYPE));
            login.add(user);
            login.add(pwd_user);
            login.add(type_user);
        }
        cursor.close();
        return login;
    }
    public long insertMessage(String subject , String message , String user){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MESSAGE_USER_NAME, user);
        values.put(MESSAGE_SUBJECT , subject);
        values.put(MESSAGE_MESSAGE , message);
        long result = db.insert(MESSAGE_TABLE_NAME , null , values);
        return result;
    }
    public List listMessages1(String subject){
        SQLiteDatabase db = getReadableDatabase();
        String [] messages = {MESSAGE_ID,MESSAGE_USER_NAME,MESSAGE_SUBJECT,MESSAGE_MESSAGE};
        String MessageSort =
                MESSAGE_ID + " DESC";
        String selection = MESSAGE_SUBJECT + " = ? ";
        String[] selectionArgs = { subject };

        Cursor cursor = db.query(
                MESSAGE_TABLE_NAME,
                messages,
                selection,
                selectionArgs,
                null,
                null,
                MessageSort
        );
        ArrayList messageView = new ArrayList<>();
        while(cursor.moveToNext()) {
            String sub = cursor.getString(cursor.getColumnIndexOrThrow(MESSAGE_SUBJECT));
            String message = cursor.getString(cursor.getColumnIndexOrThrow(MESSAGE_MESSAGE));
            messageView.add(sub);
            messageView.add(message);
        }
        cursor.close();
        return messageView;
    }

    public List listMessages(){
        SQLiteDatabase db = getReadableDatabase();
        String [] messages = {MESSAGE_ID,MESSAGE_USER_NAME,MESSAGE_SUBJECT,MESSAGE_MESSAGE};
        String MessageSort =
                MESSAGE_ID + " DESC";

        Cursor cursor = db.query(
                MESSAGE_TABLE_NAME,
                messages,
                null,
                null,
                null,
                null,
                MessageSort
        );
        List subs = new ArrayList<>();
        while(cursor.moveToNext()) {
            String subjects = cursor.getString(
                    cursor.getColumnIndex(MESSAGE_SUBJECT));
            subs.add(subjects);
        }
        cursor.close();
        return subs;
    }


}
