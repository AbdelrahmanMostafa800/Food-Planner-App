package com.example.mealmate.model;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

public class UserAuthReposatoryImp {
    FirebaseAuth auth;
    boolean isAdded;
    UserReposatoryInterface userReposatory;
    private static UserAuthReposatoryImp instance=null;
    private UserAuthReposatoryImp(){
        auth=FirebaseAuth.getInstance();
        isAdded=false;
    }
    public static synchronized UserAuthReposatoryImp getInstance() {
        if (instance == null) {
            instance = new UserAuthReposatoryImp();
        }
        return instance;
    }
    public boolean isUserAdded(){
        return isAdded;
    }

    public void addUserWithEmailPassword(String email, String password) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                isAdded=true;
            }
        });
    }
}
