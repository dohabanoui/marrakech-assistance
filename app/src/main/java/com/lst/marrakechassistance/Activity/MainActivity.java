package com.lst.marrakechassistance.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lst.marrakechassistance.Fragment.FavoritesFragment;
import com.lst.marrakechassistance.Fragment.HomeFragment;
import com.lst.marrakechassistance.Fragment.MapFragment;
import com.lst.marrakechassistance.Fragment.UserFragment;
import com.lst.marrakechassistance.R;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    HomeFragment home = new HomeFragment();
    FavoritesFragment fav = new FavoritesFragment();
    MapFragment map = new MapFragment();
    UserFragment userFragment = new UserFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    // Switch The Fragment View to the selected One from The Bottom Navigation
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home){
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, home).commit();
            return true;
        } else if (id == R.id.fav) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fav).commit();
            return true;
        } else if (id == R.id.map) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, map).commit();
            return true;
        } else if(id==R.id.user){
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, userFragment).commit();
            return true;
        }
        return false;
    }
}