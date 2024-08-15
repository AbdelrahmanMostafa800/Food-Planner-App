package com.example.mealmate.login.presenter;

import com.example.mealmate.login.view.LoginView;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter {
    LoginView view;
    FirebaseAuth auth;
    public LoginPresenter(LoginView view) {
        this.view = view;
        auth = FirebaseAuth.getInstance();
    }
}
