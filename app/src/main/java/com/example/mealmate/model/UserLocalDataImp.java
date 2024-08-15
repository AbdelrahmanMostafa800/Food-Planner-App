package com.example.mealmate.model;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class UserLocalDataImp {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferenceseditor;
    private static UserLocalDataImp instance=null;
    private UserLocalDataImp(Context context){
        sharedPreferences= context.getPreferences(MODE_PRIVATE);
        sharedPreferenceseditor= sharedPreferences.edit();
    }
    public static synchronized UserLocalDataImp getInstance() {
        if (instance == null) {
            instance = new UserLocalDataImp();
        }
        return instance;
        }

    public void addUser(String name, String email) {
    }
}
