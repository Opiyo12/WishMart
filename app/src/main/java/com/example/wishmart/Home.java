package com.example.wishmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Home extends AppCompatActivity {
   // public static final int homeDisplayFragment = 1;
   BottomNavigationView bottomNavigationView;
   homeDisplayFragment home= new homeDisplayFragment();
   CategoryFragment categoryFragment= new CategoryFragment();
   NotificationFragment notificationFragment= new NotificationFragment();
   AccountFragment accountFragment= new AccountFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        bottomNavigationView= findViewById(R.id.bottomNavBar);
        //Code for displaying the home fragment first
        getSupportFragmentManager().beginTransaction().replace(R.id.container, home).commit();
        BadgeDrawable badgeDrawable= bottomNavigationView.getOrCreateBadge(R.id.notification);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(0);

        // Get the flag indicating which fragment to display for loading the product from admin panel
        String fragmentToLoad = getIntent().getStringExtra("FRAGMENT_TO_LOAD");

        // Load the appropriate fragment based on the flag
        if (fragmentToLoad != null && fragmentToLoad.equals("HOME_PRODUCT_FRAGMENT")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new homeDisplayFragment()).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, home).commit();
        }
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,home).commit();
                        return true;
                    case R.id.category:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, categoryFragment).addToBackStack(null).commit();
                        return true;
                    case R.id.notification:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, notificationFragment).addToBackStack(null).commit();
                        return true;
                    case R.id.account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, accountFragment).addToBackStack(null).commit();
                        return true;
                }
                return false;
            }
        });

    }
}