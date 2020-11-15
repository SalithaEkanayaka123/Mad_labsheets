package com.example.a2019_pastpaper_parta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MovieList extends AppCompatActivity {
    ListView list;
    DBHelper db;
    ArrayList<String> movies;
    public static final String EXTRA_MESSAGE = "com.example.sampleemptyproject.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        list = findViewById(R.id.ListView);
        db = new DBHelper(this);
        db.viewMovies();
        movies = db.getMovies();
        ListAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,movies);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String Moviename  = adapterView.getItemAtPosition(i).toString();
                Intent intent = new Intent(MovieList.this, MovieOverview.class);
                intent.putExtra(EXTRA_MESSAGE, Moviename);
                startActivity(intent);
            }
        });
    }
}