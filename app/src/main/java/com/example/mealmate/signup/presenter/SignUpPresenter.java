package com.example.mealmate.signup.presenter;

import android.content.Context;
import com.example.mealmate.model.UserReposatoryImp;
import com.example.mealmate.model.UserReposatoryInterface;
import com.example.mealmate.signup.view.SignUpView;

public class SignUpPresenter implements SignUpPresenterInterface{
    SignUpView view;
    UserReposatoryInterface reposatory;

    public SignUpPresenter(SignUpView view, Context context){
        this.view=view;
        this.reposatory=UserReposatoryImp.getInstance(context);
    }
    @Override
    public boolean createUserWithEmailPassword(String email, String password,String name) {
       return reposatory.addUserWithEmailPassword(this,email,password,name);
    }

    @Override
    public void isuserAdded(boolean b) {
        if(b){
            view.userAddSuccess();
        }else{
            view.userAddSerror();
        }
    }


}
