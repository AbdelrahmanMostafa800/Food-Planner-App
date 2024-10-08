package com.example.mealmate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealmate.model.userrepo.UserReposatoryImp;
import com.example.mealmate.model.userrepo.UserReposatoryInterface;
import com.example.mealmate.navigationstart.onboarding.OnBoardingActivity;
import com.google.firebase.FirebaseApp;

import java.util.Objects;

public class SplashScreenActivity extends AppCompatActivity {

    UserReposatoryInterface reposatory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_splash_screen);
        reposatory= UserReposatoryImp.getInstance(this);
        String loginStatus = reposatory.getUserLoginStatus();
        if (loginStatus != null) {
            Log.d("user", loginStatus);
        } else {
            Log.d("user", "Login status is null");
        }
        new Handler().postDelayed(() -> {
            if(reposatory.getUserLoginStatus()==null|| Objects.equals(reposatory.getUserLoginStatus(), "Guest")||Objects.equals(reposatory.getUserLoginStatus(), "UserSignedOut")) {

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