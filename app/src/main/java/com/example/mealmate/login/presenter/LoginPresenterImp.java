package com.example.mealmate.login.presenter;

import android.content.Context;

import com.example.mealmate.login.view.LoginView;
import com.example.mealmate.model.UserReposatoryImp;
import com.example.mealmate.model.UserReposatoryInterface;
import com.example.mealmate.signup.presenter.SignUpPresenter;
import com.example.mealmate.signup.view.SignUpView;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenterImp implements LoginPresenter{
    LoginView view;
    UserReposatoryInterface reposatory;
    public LoginPresenterImp(LoginView view, Context context) {
        this.view = view;
        this.reposatory= UserReposatoryImp.getInstance(context);
    }

    @Override
    public void loginUser(String email, String password) {
        reposatory.loginUser(this,email,password);
    }

    @Override
    public void googleLogin(Context context) {
        reposatory.googleLogin(context);
    }

    @Override
    public void loginUserGuest( ) {
        reposatory.loginUserGuest( this);
    }

    @Override
    public void isLoginSuccess(boolean b) {
        if(b) {
            view.loginSuccess();
        }
        else {
            view.loginfail();
        }
    }
}

