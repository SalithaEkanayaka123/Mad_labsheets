package com.example.a2019_pastpaper_parta;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.a2019_pastpaper_parta.DatabaseMaster.Comments.COMMENTS_MOVIE_COMMENTS;
import static com.example.a2019_pastpaper_parta.DatabaseMaster.Comments.COMMENTS_MOVIE_NAME;
import static com.example.a2019_pastpaper_parta.DatabaseMaster.Comments.COMMENTS_MOVIE_RATING;
import static com.example.a2019_pastpaper_parta.DatabaseMaster.Comments.COMMENTS_TABLE_NAME;
import static com.example.a2019_pastpaper_parta.DatabaseMaster.Movie.MOVIE_MOVIE_NAME;
import static com.example.a2019_pastpaper_parta.DatabaseMaster.Movie.MOVIE_TABLE_NAME;
import static com.example.a2019_pastpaper_parta.DatabaseMaster.Movie.MOVIE_YEAR;
import static com.example.a2019_pastpaper_parta.DatabaseMaster.Users.USER_PASSWORD;
import static com.example.a2019_pastpaper_parta.DatabaseMaster.Users.USER_TABLE_NAME;
import static com.example.a2019_pastpaper_parta.DatabaseMaster.Users.USER_USERTYPE;
import static com.example.a2019_pastpaper_parta.DatabaseMaster.Users.USER_USER_NAME;

//DBHelper
public class DBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "salitha.db";
    ArrayList<String> comments;
    ArrayList<String> movies = new ArrayList<>();
    ArrayList<Integer> years;


    public ArrayList<String> getMovies() {
        return movies;
    }

    public ArrayList<Integer> getYears() {
        return years;
    }

    public ArrayList getComments() {
        return comments;
    }
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String CREATE_USER =
            "CREATE TABLE " + USER_TABLE_NAME
                    +" ( userId INTEGER PRIMARY KEY AUTOINCREMENT, "
                    +USER_USER_NAME + " String ," +

                    USER_PASSWORD + " String," +
                    USER_USERTYPE + " String)";
    private static final String CREATE_MOVIE =
            "CREATE TABLE " + MOVIE_TABLE_NAME
                    +" ( movieId INTEGER PRIMARY KEY AUTOINCREMENT, "
                    +MOVIE_MOVIE_NAME + " String ," +
                    MOVIE_YEAR + " Integer)";
    private static final String CREATE_COMMENT =
            "CREATE TABLE " + COMMENTS_TABLE_NAME
                    +" ( commentId INTEGER PRIMARY KEY AUTOINCREMENT, "
                    +COMMENTS_MOVIE_NAME + " String ," +
                    COMMENTS_MOVIE_RATING + " Integer," +
                    COMMENTS_MOVIE_COMMENTS + " String)";
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_MOVIE);
        db.execSQL(CREATE_COMMENT);

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL(SQL_DELETE_ENTRIES);
        //onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    public boolean registerUser(String username , String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        String usertype = "usertype";
        values.put(USER_USER_NAME, username);
        values.put(USER_PASSWORD, password);
        values.put(USER_USERTYPE, usertype);

// Insert the new row, returning the primary key value of the new row

        long result = db.insert(USER_TABLE_NAME , null, values);
        if (result > 0){
            return true;
        }else{
            return false;
        }
    }
    public String loginUser(String username , String password){

        String command = null;
        if(username.equals("admin")){
            command = "admin";
            return command;

        }
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                USER_USER_NAME,
                USER_PASSWORD,
                USER_USERTYPE
        };
        String sortOrder =
                USER_USER_NAME + " DESC";

        Cursor cursor = db.query(
                USER_TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        while(cursor.moveToNext()) {
            String un = cursor.getString(
                    cursor.getColumnIndexOrThrow(USER_USER_NAME));
            System.out.println(un);
            if (un.equals(username)){
                System.out.println("2");
                String pw = cursor.getString(
                        cursor.getColumnIndexOrThrow(USER_PASSWORD));
                System.out.println(pw);
                if (pw.equals(password)){
                    System.out.println("2");
                    command = "allok";
                    cursor.close();
                    return command;
                }else{
                    command = "errorpassword";
                    cursor.close();
                    return command;
                }
            }
            else{
                System.out.println("1");
                command =  "eusername";


            }
        }

        return command;

    }

    public boolean addMovie (String moviename , int year){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MOVIE_MOVIE_NAME, moviename);
        values.put(MOVIE_YEAR, year);

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(MOVIE_TABLE_NAME , null, values);
        if(newRowId > 0){
            return true;
        }
        else{
            return false;
        }
    }
    public void viewMovies(){
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                MOVIE_MOVIE_NAME,
                MOVIE_YEAR
        };
        String sortOrder =
                MOVIE_MOVIE_NAME + " DESC";

        Cursor cursor = db.query(
                MOVIE_TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        while(cursor.moveToNext()) {
            String movie = cursor.getString(
                    cursor.getColumnIndexOrThrow(MOVIE_MOVIE_NAME));
            movies.add(movie);
        }
        cursor.close();
    }
    public void insertComments (String moviename , int rating , String comment){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COMMENTS_MOVIE_NAME, moviename);
        values.put(COMMENTS_MOVIE_RATING, rating);
        values.put(COMMENTS_MOVIE_COMMENTS, comment);
        long newRowId = db.insert(COMMENTS_TABLE_NAME , null, values);
    }
    public Double viewComments (String moviename){

        SQLiteDatabase db = getReadableDatabase();
        comments = new ArrayList<>();
        int totalRate = 0;
        String[] projection = {
                COMMENTS_MOVIE_NAME,
                COMMENTS_MOVIE_RATING,
                COMMENTS_MOVIE_COMMENTS
        };
        String sortOrder =
                COMMENTS_MOVIE_COMMENTS + " DESC";
        String selection = COMMENTS_MOVIE_NAME + " LIKE ?";
        String[] selectionArgs = { moviename };
        Cursor cursor = db.query(
                COMMENTS_TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
        int count = 0;
        while(cursor.moveToNext()) {
            String cmts = cursor.getString(
                    cursor.getColumnIndexOrThrow(COMMENTS_MOVIE_COMMENTS));
            int rate = cursor.getInt(cursor.getColumnIndexOrThrow(COMMENTS_MOVIE_RATING));
            count++;
            totalRate = totalRate + rate;
            comments.add(cmts);
        }
        cursor.close();
        System.out.println(totalRate);
        double avg = (double)totalRate / count;
        System.out.println(avg);

        return avg;
    }
}
