package com.example.mealmate.model.userrepo;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class UserLocalDataImp {
    SharedPreferences sharedPreferences;
    SharedPreferences homeSingleMeal;
    SharedPreferences.Editor sharedPreferencesEditor;
    SharedPreferences.Editor homeSingleMealEditor;
    private static UserLocalDataImp instance=null;
    private UserLocalDataImp(Context context){
        sharedPreferences= context.getSharedPreferences("User",MODE_PRIVATE);
        homeSingleMeal=context.getSharedPreferences("Meal",MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();
        homeSingleMealEditor = homeSingleMeal.edit();
    }
    public static synchronized UserLocalDataImp getInstance(Context context) {
        if (instance == null) {
            instance = new UserLocalDataImp(context);
        }
        return instance;
        }

    public void addUser(String loginStatus,String name, String email) {
        sharedPreferencesEditor.putString("Status",loginStatus);
        sharedPreferencesEditor.putString("Name",name);
        sharedPreferencesEditor.putString("Email",email);
        sharedPreferencesEditor.commit();
    }

    public String [] getUserData() {
        String [] userData=new String[3];
        userData[0]=sharedPreferences.getString("Status",null);
        userData[1]=sharedPreferences.getString("Name",null);
        userData[2]=sharedPreferences.getString("Email",null);
        Log.d("LocalUser", "getUserData: "+userData[0]+" "+userData[1]);
        return userData;
    }

    public void addUserGuest() {
        sharedPreferencesEditor.putString("Status","Guest");
        sharedPreferencesEditor.putString("Name","Guest");
        sharedPreferencesEditor.putString("Email","Guest");
        sharedPreferencesEditor.commit();
    }
    public void addMeal(String mealId) {
        homeSingleMealEditor.putString("MealId",mealId);
        homeSingleMealEditor.commit();
    }
    public String getSavedMealId() {
        return homeSingleMeal.getString("MealId",null);
    }
}
