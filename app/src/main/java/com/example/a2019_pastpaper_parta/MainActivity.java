package com.example.a2019_pastpaper_parta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText un , pw;
    Button register , login;
    DBHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        un = findViewById(R.id.username);
        pw = findViewById(R.id.password);
        register = findViewById(R.id.registerButton);
        login = findViewById(R.id.loginButton);
        mydb = new DBHelper(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean r1 = mydb.registerUser(un.getText().toString() , pw.getText().toString());
                if (r1 == true){
                    Toast.makeText(MainActivity.this, "inserted successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = mydb.loginUser(un.getText().toString() , pw.getText().toString());
                System.out.println(type);
                if (type == "admin"){
                    Intent intent = new Intent(getApplicationContext(), addMovies.class);
                    Toast.makeText(MainActivity.this, "successfully login to AddMovie Activity", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                if (type == "allok"){
                    Intent intent = new Intent(getApplicationContext(), MovieList.class);
                    Toast.makeText(MainActivity.this, "successfully login to Movie list Activity", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                if (type == "errorpassword"){
                    Toast.makeText(MainActivity.this, "error in password", Toast.LENGTH_SHORT).show();
                }
                if (type == "eusername"){
                    Toast.makeText(MainActivity.this, "error in user name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}