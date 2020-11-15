package com.example.a2019_pastpaper_parta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class addMovies extends AppCompatActivity {
    EditText name, year;
    Button addMovies;
    DBHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movies);
        name = findViewById(R.id.movieName);
        year = findViewById(R.id.movieyear);
        addMovies = findViewById(R.id.addButton);
        mydb = new DBHelper(this);
        addMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean result = mydb.addMovie(name.getText().toString() ,Integer.parseInt(year.getText().toString()));
                if (result == true){
                    Toast.makeText(addMovies.this, "Inseting movie data successful", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(addMovies.this, "Inseting movie data unsucessful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}