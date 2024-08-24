package com.example.mealmate.model.userrepo;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    public boolean loginOut(Context context) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("781598236255-rhh2bbtfo3bj7tsmvr6ec2sn60ugeaei.apps.googleusercontent.com")
                .requestEmail()
                .build();
        GoogleSignInClient client = GoogleSignIn.getClient(context, gso);

        client.revokeAccess().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                auth.signOut();

            }
        });
            return true;
    }
}

