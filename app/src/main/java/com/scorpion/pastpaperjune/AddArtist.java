package com.scorpion.pastpaperjune;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.scorpion.pastpaperjune.Database.DBHandler;
import com.scorpion.pastpaperjune.Models.Artist;

public class AddArtist extends AppCompatActivity {

    Button add;
    EditText artistName;
    Artist artist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_artist);

        artistName = findViewById(R.id.editText);
        add = findViewById(R.id.button5);

        artist = new Artist();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                artist.setName(artistName.getText().toString());

                if (dbHandler.addArtist(artist)){
                    Toast.makeText(AddArtist.this, "Artist Added", Toast.LENGTH_SHORT).show();

                    artistName.setText(null);
                }
                else {
                    Toast.makeText(AddArtist.this, "Artist Adding Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
