package com.example.revisionlab123;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ReadingCollection extends AppCompatActivity {
    String name;
    Toast toast;

    Button book,paper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_collection);

        Intent intent = getIntent();
        name = intent.getStringExtra("ValueToPass");

        book = findViewById(R.id.buttonBook);
        paper = findViewById(R.id.buttonPaper);

    }

    @Override
    protected void onResume() {
        super.onResume();

        toast.makeText(getApplicationContext(), "Welcome" + name + "\n Please Enter what you read!!", Toast.LENGTH_SHORT).show();

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ReadingCollection.this, AddReading.class);
                intent1.putExtra("ValuePassToBook", 1);
                startActivity(intent1);
            }
        });



        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ReadingCollection.this, AddReading.class);
                intent2.putExtra("ValuePassToBook", 2);
                startActivity(intent2);
            }
        });
    }
}
