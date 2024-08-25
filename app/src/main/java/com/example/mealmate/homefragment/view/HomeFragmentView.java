package com.example.mealmate.homefragment.view;

import android.content.Context;

import com.example.mealmate.model.category.Category;
import com.example.mealmate.model.meal.Meal;

import java.util.ArrayList;

public interface HomeFragmentView {
    void showMeal(Meal meal);

    void showCategories(ArrayList<Category> category);

    void showIngrediants(ArrayList<com.example.mealmate.model.ingrediantpojo.Meal> meals);

    void showMessage(String message);
}
