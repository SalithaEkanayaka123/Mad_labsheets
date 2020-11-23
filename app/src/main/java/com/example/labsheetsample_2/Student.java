package com.example.labsheetsample_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.labsheetsample_2.database.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class Student extends AppCompatActivity {
    Intent intent , intent2;
    String username ,sub;
    TextView title;
    ListView listview;
    List listSubjects;
    private static final String CHANNEL_ID = "channel_1" ;
    DBHelper mydb;
    int c;
    public static final String EXTRA_ID_2 = "com.example.labsheet12.EXTRA_ID_2";
    Boolean result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        intent = getIntent();
        username = intent.getStringExtra(Login.EXTRA_ID);
        title = findViewById(R.id.title4);
        title.setText("Welcome " + username);
        listview = findViewById(R.id.listView1);


        viewData();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "salitha";
            String description ="SLIIT Undergraduate";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new
                    NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    public void viewData(){
        mydb = new DBHelper(this);
        listSubjects = mydb.listMessages();
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listSubjects);
        listview.setAdapter(adapter);
        String msg = listview.getItemAtPosition(0).toString();
        String notification = "Hello you have a new message";
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(Student.this, 0 , intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(Student.this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(notification)
                .setContentText("From the subject: " + msg)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(Student.this);
        notificationManagerCompat.notify(0,builder.build());
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sub = adapterView.getItemAtPosition(i).toString();
                gotoMessagePage();
            }
        });
    }
    public void gotoMessagePage(){
        intent2 = new Intent(this , Message.class);
        intent2.putExtra(EXTRA_ID_2,sub).toString();
        System.out.println(sub);
        startActivity(intent2);
    }
}