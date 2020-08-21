package com.example.revisionlab123;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddReading extends AppCompatActivity {

    TextView lblTitle, lblAuthor;
    EditText txtTitle, txtAuthor;
    Button btnView;
    //String title, author;
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reading);

        lblAuthor = findViewById(R.id.lblAuthor);
        lblTitle = findViewById(R.id.lblTitle);
        txtAuthor = findViewById(R.id.txtAuthor);
        txtTitle = findViewById(R.id.txtTitle);
        btnView = findViewById(R.id.buttonView);

        Intent intent = getIntent();
        flag = intent.getIntExtra("ValuePassToBook", -1);
        changeFragment();
    }


    private void changeFragment(){
        if(flag == 1){
            lblAuthor.setText("Author: ");
            lblTitle.setText("Book Title: ");
            Fragment fragment1;
            fragment1 = new BookFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment, fragment1);
            ft.commit();

        }
        else{
            lblTitle.setText("Title of the Article: ");
            lblAuthor.setText("Editor: ");
            Fragment fragment2;
            fragment2 = new paperFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment, fragment2);
            ft.commit();
        }
    }

}