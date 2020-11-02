package com.example.labsheetsample_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.labsheetsample_2.database.DBHelper;

import static com.example.labsheetsample_2.database.User_Table.User.USER_NAME;
import static com.example.labsheetsample_2.database.User_Table.User.USER_PASSWORD;
import static com.example.labsheetsample_2.database.User_Table.User.USER_TYPE;

public class Login extends AppCompatActivity {
    EditText eusername , epassword ;
    DBHelper mydb;
    public static final String EXTRA_ID = "com.example.labsheet12.EXTRA_ID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        eusername = findViewById(R.id.login_username);
        epassword = findViewById(R.id.login_password);
    }
    public void navigateToRegistration(View view){
        Intent intent = new Intent(this , Register.class);
        startActivity(intent);
    }
    public void validation(View view){
        mydb = new DBHelper(this);
        String username = eusername.getText().toString().trim();
        String password = epassword.getText().toString().trim();
        Cursor cursor = mydb.listUsers();
        while (cursor.moveToNext()){
            String un = cursor.getString(cursor.getColumnIndex(USER_NAME));
            String pw = cursor.getString(cursor.getColumnIndex(USER_PASSWORD));
            String type = cursor.getString(cursor.getColumnIndex(USER_TYPE));
            if (un.equals(username) && pw.equals(password)){
                if(type.equals("Teacher")){
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this , Teacher.class);
                    intent.putExtra(EXTRA_ID,username).toString();
                    startActivity(intent);
                    break;
                }
                else{
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this , Student.class);
                    intent.putExtra(EXTRA_ID,username).toString();
                    startActivity(intent);
                    break;
                }

            }
            else{
                Intent intent = new Intent(this , Login.class);
                startActivity(intent);
            }
        }
        cursor.close();
    }
}