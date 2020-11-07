package com.example.loginform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = findViewById(R.id.email) as EditText
        val password = findViewById(R.id.password) as EditText
        val loginButton = findViewById(R.id.login) as Button
        loginButton.setOnClickListener{
            val ema: String = email.text.toString();
            val pwd: String = password.text.toString();
            if (ema.trim().length == 0){
                Toast.makeText(applicationContext , "Email feild is empty" , Toast.LENGTH_SHORT).show();

            }
            if (pwd.trim().length == 0){
                Toast.makeText(applicationContext , "Password feild is empty" , Toast.LENGTH_SHORT).show();

            }
            if (ema.equals("ekanayakasalitha@gmail.com") && (pwd.equals("123@gmail.com"))){
                Toast.makeText(applicationContext , "login Successful" , Toast.LENGTH_SHORT).show();
                val intent = Intent(this , Home::class.java);
                startActivity(intent);
            }else{
                Toast.makeText(applicationContext , "login unsuccessful" , Toast.LENGTH_SHORT).show();
            }
        }
    }
}