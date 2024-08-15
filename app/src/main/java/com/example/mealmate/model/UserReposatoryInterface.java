package com.example.mealmate.model;

public interface UserReposatoryInterface {
    void addUserWithEmailPassword(String email,String password,String name);
    boolean isUserAdded();
}
