package com.example.mealmate.model;

import android.content.Context;

public interface UserReposatoryInterface {
    void addUserWithEmailPassword(String loginStatus,String email, String password, String name);

    String[] getUserLocalData();

    void loginUser(String loginStatus,String email, String password);

    void googleLogin(Context context);

    String getUserLoginStatus();
}