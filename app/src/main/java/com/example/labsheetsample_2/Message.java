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
    DBHelper mydb;
    List messages , subjects , listMessage;
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
        listMessage = mydb.listMessages1(sub);
        if (listMessage.isEmpty()){

        }else{
            message.setText(listMessage.get(1).toString());
        }
    }
}