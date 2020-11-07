package com.example.loginform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_click = findViewById<Button>(R.id.button)
        btn_click.setOnClickListener{
            val intent = Intent(this , Login::class.java);
            startActivity(intent);
        }
    }
}