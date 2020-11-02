package com.example.labsheetsample_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.labsheetsample_2.database.DBHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.labsheetsample_2.database.Message_Table.Message.MESSAGE_MESSAGE;
import static com.example.labsheetsample_2.database.Message_Table.Message.MESSAGE_SUBJECT;

public class Message extends AppCompatActivity {
    TextView text , message ;
    Intent intent;
    String subject;
    Cursor cursor;
    DBHelper mydb;
    List messages , subjects;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        intent = getIntent();
        subject = intent.getStringExtra(Student.EXTRA_ID_2);
        System.out.println(subject);
        text = findViewById(R.id.title5);
        text.setText(subject);
        mydb = new DBHelper(this);
        message = findViewById(R.id.MessageView);
        displayMessage(subject);
    }
    public void displayMessage(String sub){
        cursor = mydb.listMessages1();
        messages = new ArrayList<>();
        subjects = new ArrayList<>();
        while (cursor.moveToNext()){
            String msg = cursor.getString(cursor.getColumnIndex(MESSAGE_MESSAGE));
            String sbj = cursor.getString(cursor.getColumnIndex(MESSAGE_SUBJECT));
            messages.add(msg);
            subjects.add(sbj);
        }
        if (subjects.indexOf(sub) >=0){
            String message1 = messages.get(subjects.indexOf(sub)).toString();
            message.setText(message1);
        }
    }
}