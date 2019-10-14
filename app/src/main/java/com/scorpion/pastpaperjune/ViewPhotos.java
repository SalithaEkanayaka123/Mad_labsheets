package com.scorpion.pastpaperjune;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.scorpion.pastpaperjune.Database.DBHandler;
import com.scorpion.pastpaperjune.Models.Artist;
import com.scorpion.pastpaperjune.Models.Photograph;

import java.util.ArrayList;

public class ViewPhotos extends AppCompatActivity {

    private ArrayList<Artist> artists;
    private ArrayList<Photograph> photoList = new ArrayList<>();

    private Spinner artistNamesSpinner;
    private Button searchBtn;
    private GridView photoGridView;

    PhotographAdapter photoAdapter;

    private DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photos);

        db = new DBHandler(this);

        artistNamesSpinner = findViewById(R.id.artistNameSpinner);
        searchBtn = findViewById(R.id.searchBtn);
        photoGridView = findViewById(R.id.photoGrid);


        artists = db.loadArtistsTest();

        ArrayList<String> artistNamesList = new ArrayList<>();
        for (Artist a: artists) {
            artistNamesList.add(a.getName());
        }

        ArrayAdapter<String> artistNamesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, artistNamesList);
        artistNamesSpinner.setAdapter(artistNamesAdapter);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPhotos();
            }
        });

        // set the grid view adapter
        photoAdapter = new PhotographAdapter();
        photoGridView.setAdapter(photoAdapter);


    }

    private void searchPhotos(){
        String selectedArtistName = artistNamesSpinner.getSelectedItem().toString();

        // empty the current photo list
        photoList.removeAll(photoList);

        ArrayList<Photograph> newPhotoList = db.searchPhotographTest(selectedArtistName);

        // add the new photograps to the old list
        photoList.addAll(newPhotoList);

        // notify the adapter
        photoAdapter.notifyDataSetChanged();
    }

    private class PhotographAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return photoList.size();
        }

        @Override
        public Object getItem(int position) {
            return photoList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.photo_grid_item, null);
            }

            ImageView photoImageView = convertView.findViewById(R.id.photoImageView);
            Photograph photograph = photoList.get(position);

            // set the image
            photoImageView.setImageBitmap(getBitmap(photograph.getImage()));

            return convertView;
        }
    }

    // this will convert the byte[] we read from the db into a bitmap
    private Bitmap getBitmap(byte[] imageData){
        return BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
    }
}
