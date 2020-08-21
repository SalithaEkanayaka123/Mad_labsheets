package com.example.revisionlab123;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText textName;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.buttonOK);
        textName = findViewById(R.id.editText);

    }

    @Override
    protected void onResume() {
        super.onResume();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReadingCollection.class);
                name = textName.getText().toString();
                intent.putExtra("ValueToPass", name);

                startActivity(intent);

            }
        });


    }
}
