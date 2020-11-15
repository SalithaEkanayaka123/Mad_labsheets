package com.example.modelpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Home extends AppCompatActivity {
    EditText eusername , epassword;
    Button elogin , eregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        eusername = findViewById(R.id.username);
        epassword = findViewById(R.id.password);
        elogin = findViewById(R.id.Loginbutton);
        eregister = findViewById(R.id.Registerbutton);

    }
    public void navigateToRegister(View view){
        Intent intent = new Intent(this , ProfileManagement.class);
        startActivity(intent);
    }
}