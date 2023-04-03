package com.example.wishmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class firebaseProductSubmission extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    AdminAddProductFragment productFragment= new AdminAddProductFragment();
    AdminViewProductFragment viewProductFragment= new AdminViewProductFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_product_submission);
        bottomNavigationView= findViewById(R.id.bottomNavBar);
        //Code for displaying the home fragment first
        getSupportFragmentManager().beginTransaction().replace(R.id.container, productFragment).commit();



        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add_product:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, productFragment).commit();
                        return true;
                    case R.id.view_product:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, viewProductFragment).addToBackStack(null).commit();
                        return true;
                }
                return false;
            }
        });
    }
}