package com.example.mealmate.signup.presenter;

public interface SignUpPresenterInterface {
    boolean createUserWithEmailPassword(String email, String password,String name);

    void isuserAdded(boolean b);
}
