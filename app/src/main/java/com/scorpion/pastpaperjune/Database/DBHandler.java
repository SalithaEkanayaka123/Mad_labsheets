package com.scorpion.pastpaperjune.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.provider.BaseColumns;
import android.widget.Toast;

import com.scorpion.pastpaperjune.Models.Artist;
import com.scorpion.pastpaperjune.Models.Photograph;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Database.db";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_ARTIST);
        db.execSQL(SQL_CREATE_ENTRIES_PHOTOGRAPH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    private static final String SQL_CREATE_ENTRIES_ARTIST =
            "CREATE TABLE " + ArtistMaster.Artists.TABLE_NAME + " (" +
                    ArtistMaster.Artists._ID + " INTEGER PRIMARY KEY," +
                    ArtistMaster.Artists.COLUMN_1 + " TEXT)";

    private static final String SQL_CREATE_ENTRIES_PHOTOGRAPH =
            "CREATE TABLE " + ArtistMaster.Photographs.TABLE_NAME + " (" +
                    ArtistMaster.Photographs._ID + " INTEGER PRIMARY KEY," +
                    ArtistMaster.Photographs.COLUMN_1 + " TEXT," +
                    ArtistMaster.Photographs.COLUMN_2 + " INTEGER," +
                    ArtistMaster.Photographs.COLUMN_3 + " TEXT," +
                    ArtistMaster.Photographs.COLUMN_4 + " BLOB) ";// +
                    //"FOREIGN KEY ("+ ArtistMaster.Photographs.COLUMN_2+") " +
                    //"REFERENCES "+ ArtistMaster.Artists.TABLE_NAME +" ("+ ArtistMaster.Artists._ID +"));";

    public void addPhotos (Photograph photograph){

        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ArtistMaster.Photographs.COLUMN_1, photograph.getName());
        values.put(ArtistMaster.Photographs.COLUMN_2, photograph.getArtistId());
        values.put(ArtistMaster.Photographs.COLUMN_3, photograph.getCategory());
        values.put(ArtistMaster.Photographs.COLUMN_4, photograph.getImage());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ArtistMaster.Photographs.TABLE_NAME, null, values);

    }

    public Boolean addArtist (Artist artist) {
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ArtistMaster.Artists.COLUMN_1, artist.getName());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ArtistMaster.Artists.TABLE_NAME, null, values);

        if (newRowId >= 1 ){
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean deleteDetails (String tableName, String name){

        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        if (tableName.equals("ArtistDetails")){
            // Define 'where' part of query.
            String selection = ArtistMaster.Artists.COLUMN_1 + " LIKE ?";
            // Specify arguments in placeholder order.
            String[] selectionArgs = { name };
            // Issue SQL statement.
            int deletedRows = db.delete(ArtistMaster.Artists.TABLE_NAME, selection, selectionArgs);

            if (deletedRows >= 1 ){
                return true;
            }
            else {
                return false;
            }
        }

        else if (tableName.equals("PhotographDetails")){
            // Define 'where' part of query.
            String selection = ArtistMaster.Photographs.COLUMN_1 + " LIKE ?";
            // Specify arguments in placeholder order.
            String[] selectionArgs = { name };
            // Issue SQL statement.
            int deletedRows = db.delete(ArtistMaster.Photographs.TABLE_NAME, selection, selectionArgs);

            if (deletedRows >= 1 ){
                return true;
            }
            else {
                return false;
            }
        }

        else {
            return false;
        }

    }

    public List loadArtists (){

        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                ArtistMaster.Artists.COLUMN_1,
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = ArtistMaster.Artists.COLUMN_1 + " = ?";
        String[] selectionArgs = { "My Title" };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                ArtistMaster.Artists.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                ArtistMaster.Artists.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List artistIds = new ArrayList<>();
        List artistNames = new ArrayList<>();
        while(cursor.moveToNext()) {
            long artistId = cursor.getLong(cursor.getColumnIndexOrThrow(ArtistMaster.Artists._ID));
            String artistName = cursor.getString(cursor.getColumnIndexOrThrow(ArtistMaster.Artists.COLUMN_1));
            artistIds.add(artistId);
            artistNames.add(artistName);
        }
        cursor.close();

        return artistNames;

    }

    public List searchPhotograph (String artistName){


        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                ArtistMaster.Photographs.COLUMN_1,
                ArtistMaster.Photographs.COLUMN_2,
                ArtistMaster.Photographs.COLUMN_3,
                ArtistMaster.Photographs.COLUMN_4
        };

//        // Filter results WHERE "title" = 'My Title'
//        String selection = ArtistMaster.Artists.COLUMN_1 + " = ?";
//        String[] selectionArgs = { artistName };
//
//        // How you want the results sorted in the resulting Cursor
//        String sortOrder =
//                ArtistMaster.Photographs.COLUMN_1 + " ASC";
//
//        Cursor cursor = db.query(
//                FeedEntry.TABLE_NAME,   // The table to query
//                projection,             // The array of columns to return (pass null to get all)
//                selection,              // The columns for the WHERE clause
//                selectionArgs,          // The values for the WHERE clause
//                null,                   // don't group the rows
//                null,                   // don't filter by row groups
//                sortOrder               // The sort order
//        );

        String query = "SELECT * FROM "+ ArtistMaster.Photographs.TABLE_NAME+" , "+ ArtistMaster.Artists.TABLE_NAME +
                " WHERE "+ ArtistMaster.Artists._ID +"="+ ArtistMaster.Photographs.COLUMN_2;

        Cursor cursor = db.rawQuery(query,null);

        List photographs = new ArrayList<>();
        while(cursor.moveToNext()) {
            byte[] photograph = cursor.getBlob(cursor.getColumnIndexOrThrow(ArtistMaster.Photographs.COLUMN_4));
            photographs.add(photograph);
        }
        cursor.close();

        return photographs;
    }

    public int getArtistID (String artistName){

        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                ArtistMaster.Artists.COLUMN_1,
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = ArtistMaster.Artists.COLUMN_1 + " = ?";
        String[] selectionArgs = { artistName };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                ArtistMaster.Artists._ID + " ASC";

        Cursor cursor = db.query(
                ArtistMaster.Artists.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        int artistID = 0;
        while(cursor.moveToNext()) {
            artistID = cursor.getInt(cursor.getColumnIndexOrThrow(ArtistMaster.Artists._ID));
        }
        cursor.close();
        return artistID;
    }



    public List loadPhotoNames (){

        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                ArtistMaster.Photographs.COLUMN_1,
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = ArtistMaster.Photographs.COLUMN_1 + " = ?";
        String[] selectionArgs = { "My Title" };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                ArtistMaster.Photographs.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                ArtistMaster.Photographs.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List photoIds = new ArrayList<>();
        List photoNames = new ArrayList<>();
        while(cursor.moveToNext()) {
            long photoId = cursor.getLong(cursor.getColumnIndexOrThrow(ArtistMaster.Photographs._ID));
            String photoName = cursor.getString(cursor.getColumnIndexOrThrow(ArtistMaster.Photographs.COLUMN_1));
            photoIds.add(photoId);
            photoNames.add(photoName);
        }
        cursor.close();

        return photoNames;

    }



    public ArrayList<Artist> loadArtistsTest(){

        SQLiteDatabase db = getReadableDatabase();
        String projection[] = {
                ArtistMaster.Artists._ID,
                ArtistMaster.Artists.COLUMN_1
        };

        Cursor rows = db.query(
                ArtistMaster.Artists.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        ArrayList<Artist> artistsList = new ArrayList<>();

        while(rows.moveToNext()){
            int artistId = rows.getInt(rows.getColumnIndexOrThrow(ArtistMaster.Artists._ID));
            String artistName = rows.getString(rows.getColumnIndexOrThrow(ArtistMaster.Artists.COLUMN_1));
            Artist artist = new Artist(artistId, artistName);
            artistsList.add(artist);
        }

        return artistsList;
    }


    public ArrayList<Photograph> searchPhotographTest(String artistName){
        SQLiteDatabase db = getReadableDatabase();

        Cursor rows = db.rawQuery(
                "SELECT PhotographDetails._ID, photographName, artistId, photoCategory, image FROM PhotographDetails JOIN ArtistDetails ON PhotographDetails.artistId = ArtistDetails._ID WHERE ArtistDetails.artistName = ?",
                new String[]{artistName}
        );

        ArrayList<Photograph> photoList = new ArrayList<>();
        while(rows.moveToNext()){
            int id = rows.getInt(rows.getColumnIndexOrThrow(ArtistMaster.Photographs._ID));
            String name = rows.getString(rows.getColumnIndexOrThrow(ArtistMaster.Photographs.COLUMN_1));
            int artistId = rows.getInt(rows.getColumnIndexOrThrow(ArtistMaster.Photographs.COLUMN_2));
            String category = rows.getString(rows.getColumnIndexOrThrow(ArtistMaster.Photographs.COLUMN_3));
            byte[] image = rows.getBlob(rows.getColumnIndexOrThrow(ArtistMaster.Photographs.COLUMN_4));

            Photograph photo = new Photograph(id, name, artistId, category, image);
            photoList.add(photo);
        }

        return photoList;
    }



}
