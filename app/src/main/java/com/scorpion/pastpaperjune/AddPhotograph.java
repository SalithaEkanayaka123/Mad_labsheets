package com.scorpion.pastpaperjune;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.scorpion.pastpaperjune.Database.DBHandler;
import com.scorpion.pastpaperjune.Models.Photograph;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;

public class AddPhotograph extends AppCompatActivity {

    EditText photoName;
    Spinner artistName, photoCategory;
    ImageView photo;
    Button add;
    Bitmap image;
    Photograph photograph;

    private static final int READ_REQUEST_CODE = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photograph);

        photoName = findViewById(R.id.editText2);
        artistName = findViewById(R.id.spinner);
        photoCategory = findViewById(R.id.spinner2);
        photo = findViewById(R.id.imageView);
        add = findViewById(R.id.button6);

        photograph = new Photograph();

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterCategory = ArrayAdapter.createFromResource
                (this, R.array.categories, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        photoCategory.setAdapter(adapterCategory);



        DBHandler dbHandler = new DBHandler(getApplicationContext());

        List artists = dbHandler.loadArtists();
        ArrayAdapter<List> adapterArtists = new ArrayAdapter(this,android.R.layout.simple_list_item_1,artists);

        // Specify the layout to use when the list of choices appears
        adapterArtists.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        artistName.setAdapter(adapterArtists);


        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("image/*");

                    startActivityForResult(intent, READ_REQUEST_CODE);
                }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());

                int artistID = dbHandler.getArtistID(artistName.getSelectedItem().toString());

                photograph.setName(photoName.getText().toString());
                photograph.setArtistId(artistID);
                photograph.setCategory(photoCategory.getSelectedItem().toString());

                byte[] img = getImageByteArray();
                photograph.setImage(img);

                dbHandler.addPhotos(photograph);
                Toast.makeText(AddPhotograph.this, "Photograph Added", Toast.LENGTH_SHORT).show();

                photoName.setText(null);

                Intent i = new Intent(getApplicationContext(),Main.class);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultData) {

        super.onActivityResult(requestCode, resultCode, resultData);
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                photo.setImageURI(uri);

                try {
                    image = getBitmapFromUri(uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    // this will convert the bitmap image into a byte[], because we cannot directly save bitmap in SQLITE
    private byte[] getImageByteArray(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
}
