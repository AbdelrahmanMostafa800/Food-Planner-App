package com.example.mealmate.network;

import com.example.mealmate.model.meal.Meal;

import java.util.ArrayList;

public interface SearchFragmentNetworkCallBack {
    void onSuccessResultOfgetMealsByFirstLetter(ArrayList<Meal> meals);

    void onFailureResult(String message);
}
