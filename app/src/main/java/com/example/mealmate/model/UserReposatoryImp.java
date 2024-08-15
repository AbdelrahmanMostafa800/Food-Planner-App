package com.example.mealmate.model;

import android.util.Log;

public class UserReposatoryImp implements UserReposatoryInterface{
    UserAuthReposatoryImp userAuth;
    UserLocalDataImp userLocal;
    private static UserReposatoryImp instance=null;
    private UserReposatoryImp(UserAuthReposatoryImp userAuth, UserLocalDataImp userLocal){
        this.userAuth=userAuth;
        this.userLocal=userLocal;
    }
    public static synchronized UserReposatoryImp getInstance() {
        if (instance == null) {
            instance = new UserReposatoryImp(UserAuthReposatoryImp.getInstance(), UserLocalDataImp.getInstance());
        }
        return instance;
    }
    @Override
    public void addUserWithEmailPassword(String email, String password, String name) {
        userAuth.addUserWithEmailPassword(email,password);
        userLocal.addUser(name,email);
    }

    @Override
    public boolean isUserAdded() {
        if(userAuth.isUserAdded()){
            Log.d("ADDUSER", "addUserWithEmail: true");
            return true;

        }
        Log.d("ADDUSER", "addUserWithEmail: false");
        return false;
    }
}
