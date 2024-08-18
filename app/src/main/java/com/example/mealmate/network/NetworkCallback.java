package com.example.mealmate.network;

import com.example.mealmate.model.Meal;

public interface NetworkCallback {
    void onSuccessResult(Meal meal);
    void onFailureResult(String errorMsg);

}
