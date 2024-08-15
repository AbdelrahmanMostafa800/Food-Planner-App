package com.example.mealmate;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class OnBoardingActivity extends AppCompatActivity {
    NavController navview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
//        navview= findViewById(R.id.nav_host_fragment);
//        NavController navControUer = Navigation.findNavController( this,R.id.nav_host_fragment);
//        NavigationUI.setupWithNavController(navview, navControUer);
//        navview = Navigation.findNavController(this,R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this,navview);
    }
}