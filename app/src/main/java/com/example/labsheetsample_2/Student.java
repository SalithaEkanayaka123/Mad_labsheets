package com.example.labsheetsample_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.labsheetsample_2.database.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class Student extends AppCompatActivity {
    Intent intent , intent2;
    String username ,sub;
    TextView title;
    ListView listview;
    List listSubjects;
    DBHelper mydb;
    public static final String EXTRA_ID_2 = "com.example.labsheet12.EXTRA_ID_2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        intent = getIntent();
        username = intent.getStringExtra(Login.EXTRA_ID);
        title = findViewById(R.id.title4);
        title.setText("Welcome " + username);
        listview = findViewById(R.id.listView1);
        viewData();
    }
    public void viewData(){
        mydb = new DBHelper(this);
        listSubjects = mydb.listMessages();
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listSubjects);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sub = adapterView.getItemAtPosition(i).toString();
                gotoMessagePage();
            }
        });
    }
    public void gotoMessagePage(){
        intent2 = new Intent(this , Message.class);
        intent2.putExtra(EXTRA_ID_2,sub).toString();
        System.out.println(sub);
        startActivity(intent2);
    }
}