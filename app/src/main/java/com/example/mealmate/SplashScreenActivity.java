package com.example.mealmate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealmate.onboarding.OnBoardingActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
        new Handler().postDelayed(() -> {
            Intent intent= new Intent(this, OnBoardingActivity.class);
            startActivity(intent);
            finish();
        }, 9000);


    }
}