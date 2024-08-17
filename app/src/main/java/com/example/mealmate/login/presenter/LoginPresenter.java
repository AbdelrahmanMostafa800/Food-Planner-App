package com.example.mealmate.login.presenter;

import android.content.Context;

public interface LoginPresenter {

    void loginUser(String loginStatus,String email, String password);

    void googleLogin(Context context);
}
