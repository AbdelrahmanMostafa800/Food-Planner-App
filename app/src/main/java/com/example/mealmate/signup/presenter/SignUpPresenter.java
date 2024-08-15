package com.example.mealmate.signup.presenter;

import com.example.mealmate.model.UserAuthReposatoryImp;
import com.example.mealmate.model.UserReposatoryImp;
import com.example.mealmate.model.UserReposatoryInterface;
import com.example.mealmate.signup.view.SignUpView;

public class SignUpPresenter implements SignUpPresenterInterface{
    SignUpView view;
    UserReposatoryInterface reposatory;
    public SignUpPresenter(SignUpView view){
        this.view=view;
        this.reposatory=UserReposatoryImp.getInstance();
    }
    @Override
    public void createUserWithEmailPassword(String email, String password,String name) {
        reposatory.addUserWithEmailPassword(email,password,name);
    }
    @Override
    public boolean isUserAdded(){
        return reposatory.isUserAdded();
    }
}
