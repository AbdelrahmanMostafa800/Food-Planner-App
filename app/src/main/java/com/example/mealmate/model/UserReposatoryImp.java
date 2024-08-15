package com.example.mealmate.model;

import android.content.Context;
import android.util.Log;

import java.util.Arrays;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class UserReposatoryImp implements UserReposatoryInterface{
    UserAuthReposatoryImp userAuth;
    UserLocalDataImp userLocal;
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
    public void addUserWithEmailPassword(String email, String password, String name) {
        UserAuthReposatoryImp instance = UserAuthReposatoryImp.getInstance();
        userAuth.addUserWithEmailPassword(email,password)
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // Called when the subscription is established
                    }

                    @Override
                    public void onComplete() {
                        Log.d("userrepo", "onComplete: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("userrepo", "onError: ");
                    }
                });

        userLocal.addUser(name,email);
    }
    @Override
    public String[] getUserLocalData() {
        return userLocal.getUserData();
    }

    @Override
    public void loginUser(String email, String password) {
        UserAuthReposatoryImp instance = UserAuthReposatoryImp.getInstance();
        userAuth.loginUser(email,password)
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // Called when the subscription is established
                    }

                    @Override
                    public void onComplete() {
                        Log.d("userrepo", "onComplete:signed in ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("userrepo", "onError: ");
                    }
                });
    }


}
