package com.example.mealmate.login.presenter;

import android.content.Context;

public interface LoginPresenter {

    void loginUser(String email, String password);

    void googleLogin(Context context);

    void loginUserGuest();

    void isLoginSuccess(boolean b);
}
