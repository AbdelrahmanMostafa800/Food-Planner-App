package com.example.mealmate.model.userrepo;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mealmate.login.presenter.LoginPresenter;
import com.example.mealmate.db.remotedb.RemotaDbDataSource;
import com.example.mealmate.signup.presenter.SignUpPresenterInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

import java.util.Map;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class UserReposatoryImp implements UserReposatoryInterface{
    UserAuthReposatoryImp userAuth;
    UserLocalDataImp userLocal;
    RemotaDbDataSource dbFirebaseRemote;
    boolean userIsAdded;
    LoginPresenter presenter;
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
        userAuth.addUserWithEmailPassword(email,password)
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // Called when the subscription is established
                    }

                    @Override
                    public void onComplete() {
                        userLocal.addUser("UserSignedUp",name,email);
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
    public void googleLogin() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            user.getIdToken(true)
                    .addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                        @Override
                        public void onComplete(@NonNull Task<GetTokenResult> task) {
                            if (task.isSuccessful()) {
                                GetTokenResult result = task.getResult();
                                Map<String, Object> claims = result.getClaims();
                                Object createdAtObject = claims.get("created_at");
                                Object lastSignInAtObject = claims.get("last_sign_in_at");

                                long creationTime = 0;
                                long lastSignInTime = 0;

                                if (createdAtObject != null && createdAtObject instanceof Long) {
                                    creationTime = (Long) createdAtObject;
                                }

                                if (lastSignInAtObject != null && lastSignInAtObject instanceof Long) {
                                    lastSignInTime = (Long) lastSignInAtObject;
                                }

                                // Check if user is new or old
                                if (creationTime == lastSignInTime) {
                                    // User is new
                                    userLocal.addUser("UserSignedUp", "", user.getUid());
                                } else {
                                    // User is old
                                    userLocal.addUser("UserLogidIn", "", user.getUid());
                                }
                            } else {
                                // Handle error
                            }
                        }
                    });
        } else {
            // User is signed out
            userLocal.addUser("UserSignedOut", "", "");
        }
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

@Override
    public String getUserID(){
   return userAuth.getUserId();
}

@Override
    public void addMeal(String mealId) {
    Log.d("idMeal", "getSingleMeal: "+mealId);
        userLocal.addMeal(mealId);
    }
    @Override
    public String getSavedMealId() {
        Log.d("idMeal", "getSingleMeal: "+userLocal.getSavedMealId());
        return userLocal.getSavedMealId();
    }

}
