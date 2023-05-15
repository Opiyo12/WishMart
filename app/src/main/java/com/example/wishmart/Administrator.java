package com.example.wishmart;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Administrator extends AppCompatActivity {
 LinearLayout upload,home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);
        upload= findViewById(R.id.upload);
        home= findViewById(R.id.home);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Administrator.this, AdminProductUpload.class);
                startActivity(i);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Administrator.this, Home.class);
                intent.putExtra("FRAGMENT_TO_LOAD", "HOME_PRODUCT_FRAGMENT");
                startActivity(intent);

            }
        });

    }
}