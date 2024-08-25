package com.example.mealmate.homefragmentselectedchip.presenter;


import android.content.Context;

import com.example.mealmate.homefragmentselectedchip.view.ShowFilterChipActivity;
import com.example.mealmate.model.meal.Meal;

public interface ShowFilterChippresenterInterface {

    void getFilterByCategory(String query,String strCategory);

    void getMealById(String mealId, Context context);

    void insertMealTocalender(String day, String mealId, ShowFilterChipActivity showFilterChipActivity);
}
