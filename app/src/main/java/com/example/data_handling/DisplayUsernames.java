package com.example.data_handling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.data_handling.Database.DBHandler;

import java.util.ArrayList;
import java.util.List;

public class DisplayUsernames extends AppCompatActivity {
    ListView listView;
    ArrayList<String> list;
    DBHandler mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_usernames);
        listView = findViewById(R.id.listView);
        //list = new ArrayList<>();
        mydb = new DBHandler(this);
        list = mydb.ListUsers();
        ListAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String user  = adapterView.getItemAtPosition(i).toString();
                System.out.println(user);
            }
        });
    }
}