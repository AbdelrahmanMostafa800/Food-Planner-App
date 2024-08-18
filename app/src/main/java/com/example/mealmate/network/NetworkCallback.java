package com.example.mealmate.network;

import com.example.mealmate.model.Category;
import com.example.mealmate.model.CategoryList;
import com.example.mealmate.model.Meal;

import java.util.ArrayList;

public interface NetworkCallback {
    void onSuccessResult(Meal meal);
    void onFailureResult(String errorMsg);
    void onRequestCategorySuccessResult(ArrayList<Category> category);

}
