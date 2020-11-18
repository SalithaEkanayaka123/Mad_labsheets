package com.example.labsheet_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void changeView(View view){
        Fragment1 fg1;
        Fragment2 fg2;
        if (view == findViewById(R.id.button1)){
            fg1 = new Fragment1();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentDefault,fg1);
            ft.commit();
        }
        if (view == findViewById(R.id.button2)){
            fg2 = new Fragment2();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentDefault,fg2);
            ft.commit();
        }
    }
}