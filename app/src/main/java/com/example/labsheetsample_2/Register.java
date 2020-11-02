package com.example.labsheetsample_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.labsheetsample_2.database.DBHelper;

public class Register extends AppCompatActivity {
    EditText eusername , epassword ;
    RadioButton teacher , student;
    String type ;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        eusername = findViewById(R.id.register_username);
        epassword = findViewById(R.id.register_password);
        teacher = findViewById(R.id.radioButton_teacher1);
        student = findViewById(R.id.radioButton_Student1);
    }
    public void insertData(View view){
        dbHelper = new DBHelper(this);
        if (teacher.isChecked()){
            type = teacher.getText().toString().trim();
            teacher.setChecked(true);
            student.setChecked(false);
        }
        if (student.isChecked()){
            type = student.getText().toString().trim();
            teacher.setChecked(false);
            student.setChecked(true);
        }
        long result = dbHelper.insertUsers(eusername.getText().toString().trim() , epassword.getText().toString().trim() , type);
        if(result > 0){
            Toast.makeText(this, "registration successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this , Login.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "registration unsuccessful", Toast.LENGTH_SHORT).show();
        }
    }
}