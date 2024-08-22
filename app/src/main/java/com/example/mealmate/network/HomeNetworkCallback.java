package com.example.mealmate.network;

import com.example.mealmate.model.category.Category;

import java.util.ArrayList;

public interface HomeNetworkCallback{
    void onFailureResult(String errorMsg);
    void onRequestIngrediantSuccessResult(ArrayList<com.example.mealmate.model.ingrediantpojo.Meal> meals);
}
