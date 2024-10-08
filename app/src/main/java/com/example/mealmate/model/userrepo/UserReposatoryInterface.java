package com.example.mealmate.model.userrepo;

import android.content.Context;

import com.example.mealmate.login.presenter.LoginPresenter;
import com.example.mealmate.signup.presenter.SignUpPresenterInterface;

public interface UserReposatoryInterface {
    boolean addUserWithEmailPassword(SignUpPresenterInterface spresenter,String email, String password, String name);

    String[] getUserLocalData();

    void loginUser(LoginPresenter lpresenter, String email, String password);

    void googleLogin();

    String getUserLoginStatus();

    void loginUserGuest(LoginPresenter lpresenter);

    String getUserID();

    void addMeal(String mealId);

    String getSavedMealId();

    boolean loginOut(Context context);
}