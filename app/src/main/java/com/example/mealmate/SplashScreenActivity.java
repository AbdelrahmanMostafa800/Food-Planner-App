package com.example.mealmate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealmate.model.userrepo.UserReposatoryImp;
import com.example.mealmate.model.userrepo.UserReposatoryInterface;
import com.example.mealmate.navigationstart.onboarding.OnBoardingActivity;
import com.google.firebase.FirebaseApp;

public class SplashScreenActivity extends AppCompatActivity {

    UserReposatoryInterface reposatory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_splash_screen);
        reposatory= UserReposatoryImp.getInstance(this);
        new Handler().postDelayed(() -> {
            if(reposatory.getUserLoginStatus()==null) {
                Intent intent = new Intent(this, OnBoardingActivity.class);
                startActivity(intent);
            }else{
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }
            finish();
        }, 9000);


    }
}