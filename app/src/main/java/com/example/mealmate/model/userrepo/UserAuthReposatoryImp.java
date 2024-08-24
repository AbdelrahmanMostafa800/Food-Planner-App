package com.example.mealmate.model.userrepo;

import com.google.firebase.auth.FirebaseAuth;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UserAuthReposatoryImp implements UserAuthReposatoryInterface{
    FirebaseAuth auth;
    private static UserAuthReposatoryImp instance=null;
    private UserAuthReposatoryImp(){
        auth=FirebaseAuth.getInstance();
    }
    public static synchronized UserAuthReposatoryImp getInstance() {
        if (instance == null) {
            instance = new UserAuthReposatoryImp();
        }
        return instance;
    }

    public Completable addUserWithEmailPassword(String email, String password) {
        return Completable.create(emitter -> {
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            emitter.onComplete();
                        } else {
                            emitter.onError(task.getException());
                        }
                    });
        }).subscribeOn(Schedulers.io());
    }



    public Completable loginUser(String email, String password) {
        return Completable.create(emitter -> {
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            emitter.onComplete();
                        } else {
                            emitter.onError(task.getException());
                        }
                    });
        }).subscribeOn(Schedulers.io());
    }

    public void googleLogin() {

    }

    public String getUserId() {
        return auth.getCurrentUser().getUid();
    }

    @Override
    public boolean loginOut() {
        auth.signOut();
        boolean isLoggedOut = auth.getCurrentUser() == null;
        if (isLoggedOut) {
            return true;
        } else {
            return false;
        }
    }
}

