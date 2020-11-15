package com.example.modelpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.modelpaper.Database.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class EditProfile extends AppCompatActivity {
    EditText eusername , epassword , eDOB;
    RadioButton emale , efemale;
    Button edit , delete , search;
    DBHelper mydb;
    String gender , DOB , password;
    List userlsit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        eusername =  findViewById(R.id.username2);
        epassword =  findViewById(R.id.password2);
        eDOB =  findViewById(R.id.DOB2);
        emale =  findViewById(R.id.male2);
        efemale =  findViewById(R.id.female2);
        edit = findViewById(R.id.editbtn);
        delete = findViewById(R.id.deletebtn);
        search = findViewById(R.id.Searchbutton);
        mydb = new DBHelper(this);
    }
    public void searchUser(View view){
        userlsit = new ArrayList();
        userlsit = mydb.readAllInfor(eusername.getText().toString());
        if (userlsit.isEmpty()){
            Toast.makeText(this, "User details empty", Toast.LENGTH_SHORT).show();
            eusername.setText(null);
        }else{
            eDOB.setText(userlsit.get(1).toString());
            System.out.println(userlsit.get(1).toString());
            epassword.setText(userlsit.get(2).toString());
            System.out.println(userlsit.get(2).toString());
            System.out.println(userlsit.get(3).toString());
            if (userlsit.get(3).toString().equals("male")){
                System.out.println("rrrrr");
                emale.setChecked(true);
                efemale.setChecked(false);
            }else{
                emale.setChecked(false);
                efemale.setChecked(true);
            }
        }
    }
    public void updateData(View view){
        if (efemale.isChecked()){
            gender = "female" ;
        }
        else{
            gender = "male" ;
        }
        boolean result  = mydb.updateInfor(eusername.getText().toString() , eDOB.getText().toString() , epassword.getText().toString() , gender);
        if (result){
            Toast.makeText(this, "updating data successful", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "updating data unsuccessful", Toast.LENGTH_SHORT).show();
        }
        eusername.setText(null);
        epassword.setText(null);
        eDOB.setText(null);
    }
    public void deleteData(View view){
        boolean result  = mydb.deleteInfo(eusername.getText().toString());
        if (result){
            Toast.makeText(this, "deleting data successful", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "deleting data unsuccessful", Toast.LENGTH_SHORT).show();
        }
        eusername.setText(null);
        epassword.setText(null);
        eDOB.setText(null);
    }
}