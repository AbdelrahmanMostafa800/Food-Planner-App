package com.example.mealmate.login.presenter;

import android.content.Context;

public interface LoginPresenter {

    void loginUser(String email, String password);

    void googleLogin();

    void loginUserGuest();

    void isLoginSuccess(String b);
}
