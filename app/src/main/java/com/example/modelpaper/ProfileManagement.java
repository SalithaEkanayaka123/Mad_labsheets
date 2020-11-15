package com.example.modelpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.modelpaper.Database.DBHelper;

public class ProfileManagement extends AppCompatActivity {
    EditText eusername , epassword , eDOB;
    RadioButton emale , efemale;
    Button add , editPage;
    DBHelper mydb;
    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);
        eusername =  findViewById(R.id.username1);
        epassword =  findViewById(R.id.password1);
        eDOB =  findViewById(R.id.DOB);
        emale =  findViewById(R.id.male);
        efemale =  findViewById(R.id.female);
        add = findViewById(R.id.Register);
        editPage = findViewById(R.id.updateProfile);
        mydb = new DBHelper(this);
    }
    public void insertData(View view){
        if (efemale.isChecked()){
            gender = "female" ;
            efemale.setChecked(true);
            emale.setChecked(false);
        }
        else{
            gender = "male" ;
            efemale.setChecked(false);
            emale.setChecked(true);
        }
        boolean result  = mydb.addInfo(eusername.getText().toString() , eDOB.getText().toString() , epassword.getText().toString() , gender);
        if (result){
            Toast.makeText(this, "inserting data successful", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "inserting data unsuccessful", Toast.LENGTH_SHORT).show();
        }
        eusername.setText(null);
        epassword.setText(null);
        eDOB.setText(null);
    }
    public void NavigateTOEdit(View view){
        Intent intent = new Intent(this , EditProfile.class);
        startActivity(intent);
    }

}