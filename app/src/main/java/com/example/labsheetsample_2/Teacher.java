package com.example.labsheetsample_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labsheetsample_2.database.DBHelper;

public class Teacher extends AppCompatActivity {
    Intent intent;
    String username , subject , message;
    TextView title;
    EditText esubject , emessage ;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        intent = getIntent();
        username = intent.getStringExtra(Login.EXTRA_ID);
        title = findViewById(R.id.title3);
        title.setText("Welcome " + username);
        esubject = findViewById(R.id.teacher_subject);
        emessage = findViewById(R.id.teacher_message);
    }
    public void insertMessage(View view){
        dbHelper = new DBHelper(this);
        subject = esubject.getText().toString().trim();
        message = emessage.getText().toString().trim();
        long result = dbHelper.insertMessage(subject , message , username);
        if(result > 0){
            Toast.makeText(this, "adding message is successful", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "adding message is unsuccessful", Toast.LENGTH_SHORT).show();
        }
    }
}