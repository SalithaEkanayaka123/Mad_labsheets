package com.example.data_handling;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AlertDialogLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.data_handling.Database.DBHandler;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText username , password;
    Button add , update , delete , signup , selectall;
    DBHandler mydb;
    Boolean result;
    List users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        add = findViewById(R.id.add);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        signup = findViewById(R.id.signup);
        selectall = findViewById(R.id.selectall);
        mydb = new DBHandler(this);
    }
    public void addDetails(View view){
        result = mydb.UserdetailsAdded(username.getText().toString() , password.getText().toString());
        if (result == true){
            //Toast.makeText(this, "User details added to system", Toast.LENGTH_SHORT).show();
            AlertDialog dialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Data");
            builder.setMessage("User details added");
            builder.setPositiveButton("ok", null);
            dialog = builder.create();
            dialog.show();
        }else{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
    public void updateDetails(View view){
        result = mydb.UserdetailsUpdated(username.getText().toString() , password.getText().toString());
        if (result == true){
            Toast.makeText(this, "User details updated to system", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
    public void deleteDetails(View view){
        result = mydb.UserdetailsDeleted(username.getText().toString());
        if (result == true){
            Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
    public void signup(View view){
        users = mydb.finduser(username.getText().toString() , password.getText().toString());
        if (users.isEmpty()){
            Toast.makeText(this, "Invalid login with " + username.getText().toString()+ "and password: " + password.getText().toString(), Toast.LENGTH_SHORT).show();
        }else{
            String username = users.get(0).toString();
            String password = users.get(1).toString();
            Toast.makeText(this, "valid login! - username: "+ username +" password: "+ password, Toast.LENGTH_SHORT).show();
        }

    }
    public void listDetails(View view){
        Intent intent = new Intent(MainActivity.this , DisplayUsernames.class);
        startActivity(intent);
    }
}