package com.example.a2019_pastpaper_parta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieOverview extends AppCompatActivity {
    TextView title , currentRating;
    SeekBar rate;
    int rating = 0;
    EditText cmt;
    Button Submit;
    String message;
    DBHelper db ;
    ArrayList<String> comments;
    ListView list;
    Double average;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_overview);
        title = findViewById(R.id.textView3);
        list = findViewById(R.id.listView2);
        currentRating = findViewById(R.id.currentRating);
        db = new DBHelper(this);

        Intent intent = getIntent();
        message = intent.getStringExtra(MovieList.EXTRA_MESSAGE);
        title.setText(message);
        rate = findViewById(R.id.Rate);
        cmt = findViewById(R.id.comment);
        Submit = findViewById(R.id.buttonSubmit);


        rate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                rating = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        viewComments();
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.insertComments(message, rating , cmt.getText().toString());
                viewComments();
            }
        });
    }
    public void viewComments(){
        average = db.viewComments(message);
        comments = new ArrayList<String>();
        comments = db.getComments();

        System.out.println(average);
        ListAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,comments);
        list.setAdapter(adapter);
        currentRating.setText(Double.toString(average));


    }
}