package com.example.mealmate.network;

import com.example.mealmate.model.category.Category;

import java.util.ArrayList;

public interface HomeNetworkCallback{
    <T>void onSuccessResult(ArrayList<T> meal);



    void onFailureResult(String errorMsg);

    void onRequestCategorySuccessResult(ArrayList<Category> categories);

    void onRequestIngrediantSuccessResult(ArrayList<com.example.mealmate.model.ingrediantpojo.Meal> meals);
}
