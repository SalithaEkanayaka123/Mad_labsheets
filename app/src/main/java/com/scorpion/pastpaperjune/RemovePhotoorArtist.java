package com.scorpion.pastpaperjune;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.scorpion.pastpaperjune.Database.ArtistMaster;
import com.scorpion.pastpaperjune.Database.DBHandler;

import java.util.List;

public class RemovePhotoorArtist extends AppCompatActivity {

    Spinner photonames , artistnames;
    Button deletePhoto, deleteArtist;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_photoor_artist);

        photonames = findViewById(R.id.spinner3);
        artistnames = findViewById(R.id.spinner4);
        deletePhoto = findViewById(R.id.button7);
        deleteArtist = findViewById(R.id.button8);

        dbHandler = new DBHandler(getApplicationContext());

        //list Artist Names
        List artists = dbHandler.loadArtists();
        ArrayAdapter<List> adapterArtists = new ArrayAdapter(this,android.R.layout.simple_list_item_1,artists);

        // Specify the layout to use when the list of choices appears
        adapterArtists.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        artistnames.setAdapter(adapterArtists);


        //list photograph Names
        List photos = dbHandler.loadPhotoNames();
        ArrayAdapter<List> adapterPhotos = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,photos);
        // Specify the layout to use when the list of choices appears
        adapterPhotos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        photonames.setAdapter(adapterPhotos);


        deletePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler1 = new DBHandler(getApplicationContext());
                if (dbHandler1.deleteDetails(ArtistMaster.Photographs.TABLE_NAME,photonames.getSelectedItem().toString())){
                    Toast.makeText(RemovePhotoorArtist.this, "Photo Deleted", Toast.LENGTH_SHORT).show();

                    List artists = dbHandler.loadArtists();
                    ArrayAdapter<List> adapterArtists = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,artists);

                    // Specify the layout to use when the list of choices appears
                    adapterArtists.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // Apply the adapter to the spinner
                    artistnames.setAdapter(adapterArtists);
                }
                else {
                    Toast.makeText(RemovePhotoorArtist.this, "Photo Deletion Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });

        deleteArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler1 = new DBHandler(getApplicationContext());
                if (dbHandler1.deleteDetails(ArtistMaster.Artists.TABLE_NAME,artistnames.getSelectedItem().toString())){
                    Toast.makeText(RemovePhotoorArtist.this, "Artist Deleted", Toast.LENGTH_SHORT).show();

                    List artists = dbHandler.loadArtists();
                    ArrayAdapter<List> adapterArtists = new ArrayAdapter( getApplicationContext() ,android.R.layout.simple_list_item_1,artists);

                    // Specify the layout to use when the list of choices appears
                    adapterArtists.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // Apply the adapter to the spinner
                    artistnames.setAdapter(adapterArtists);
                }
                else {
                    Toast.makeText(RemovePhotoorArtist.this, "Artist Deletion Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
