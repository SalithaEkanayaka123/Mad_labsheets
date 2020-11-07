package com.example.labsheet10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText value;
    Button convertButton;
    RadioButton ecelcious, efarenheit;
    TextView display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        value = findViewById(R.id.value);
        convertButton = findViewById(R.id.Covert_Button);
        ecelcious = findViewById(R.id.celsius);
        efarenheit = findViewById(R.id.fahrenheit);
        display = findViewById(R.id.textView);
    }
    public static float convertToCelsius(float value){
        return ((value - 32) * 5/9);
    }
    public static float convertToFarenheit(float value){
        return ((value * 9) / 5) + 32;
    }
    public void calculateValue(View view){
        if(value.getText().length() == 0){
            Toast.makeText(this, "please enter a value", Toast.LENGTH_SHORT).show();

        }
        else{
           float result = Float.parseFloat(value.getText().toString());
            System.out.println(result);
           if (ecelcious.isChecked()){
               display.setText("temperature is " + String.valueOf(convertToCelsius(result)) + "C");
               ecelcious.setChecked(true);
               efarenheit.setChecked(false);
           }
            if (efarenheit.isChecked()){
                display.setText("temperature is " + String.valueOf(convertToFarenheit(result)) + "F");
                ecelcious.setChecked(false);
                efarenheit.setChecked(true);
            }
        }
    }
}