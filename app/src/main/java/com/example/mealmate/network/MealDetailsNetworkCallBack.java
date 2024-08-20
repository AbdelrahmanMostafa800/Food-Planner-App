package com.example.mealmate.network;

import com.example.mealmate.model.Meal;

public interface MealDetailsNetworkCallBack {
    void onSuccessResultOfgetMealDetails(Meal meal);

    void onFailureResult(String message);
}
