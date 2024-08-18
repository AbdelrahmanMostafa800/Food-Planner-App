package com.example.mealmate.model;

import android.content.Context;
import android.util.Log;

import com.example.mealmate.login.presenter.LoginPresenter;
import com.example.mealmate.remotedb.RemotaDbDataSource;
import com.example.mealmate.signup.presenter.SignUpPresenter;
import com.example.mealmate.signup.presenter.SignUpPresenterInterface;
import com.example.mealmate.signup.view.SignUpView;

import java.util.Arrays;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class UserReposatoryImp implements UserReposatoryInterface{
    UserAuthReposatoryImp userAuth;
    UserLocalDataImp userLocal;
    RemotaDbDataSource dbFirebaseRemote;
    boolean userIsAdded;
    private static UserReposatoryImp instance=null;
    private UserReposatoryImp(UserAuthReposatoryImp userAuth, UserLocalDataImp userLocal){
        this.userAuth=userAuth;
        this.userLocal=userLocal;
    }
    public static synchronized UserReposatoryImp getInstance(Context context) {
        if (instance == null) {
            instance = new UserReposatoryImp(UserAuthReposatoryImp.getInstance(), UserLocalDataImp.getInstance(context));
        }
        return instance;
    }
    @Override
    public boolean addUserWithEmailPassword(SignUpPresenterInterface spresenter,String email, String password, String name) {
        UserAuthReposatoryImp instance = UserAuthReposatoryImp.getInstance();
        userAuth.addUserWithEmailPassword(email,password)
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // Called when the subscription is established
                    }

                    @Override
                    public void onComplete() {
                        userLocal.addUser("UserLogidIn",name,email);
                        Log.d("userrepo", "onComplete: ");
                        spresenter.isuserAdded(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("userrepo", "onError: ");
                        spresenter.isuserAdded(false);
                    }
                });

            return userIsAdded;
    }
    @Override
    public String[] getUserLocalData() {
        return userLocal.getUserData();
    }

    @Override
    public void loginUser(LoginPresenter lpresenter, String email, String password) {
        userAuth.loginUser(email,password)
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // Called when the subscription is established
                    }

                    @Override
                    public void onComplete() {
                        userLocal.addUser("UserLogidIn","",email);
                        lpresenter.isLoginSuccess(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("userrepo", "onError: ");
                        lpresenter.isLoginSuccess(false);
                    }
                });
    }

    @Override
    public void googleLogin(Context context) {
        userAuth.googleLogin(context);
    }

    @Override
    public String getUserLoginStatus() {
        String[] userdata=userLocal.getUserData();
        return userdata[0];
    }

    @Override
    public void loginUserGuest(LoginPresenter lpresenter) {
        userLocal.addUserGuest();
        lpresenter.isLoginSuccess(true);
    }


}
