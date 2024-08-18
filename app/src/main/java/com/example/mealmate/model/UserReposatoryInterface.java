package com.example.mealmate.model;

import android.content.Context;

import com.example.mealmate.login.presenter.LoginPresenter;
import com.example.mealmate.signup.presenter.SignUpPresenterInterface;

public interface UserReposatoryInterface {
    boolean addUserWithEmailPassword(SignUpPresenterInterface spresenter,String email, String password, String name);

    String[] getUserLocalData();

    void loginUser(LoginPresenter lpresenter, String email, String password);

    void googleLogin(Context context);

    String getUserLoginStatus();

    void loginUserGuest(LoginPresenter lpresenter);
}