package com.example.mealmate.homefragment.view;

import com.example.mealmate.model.Category;
import com.example.mealmate.model.Meal;

import java.util.ArrayList;

public interface HomeFragmentView {
    void showMeal(Meal meal);

    void showCategories(ArrayList<Category> category);

    void showIngrediants(ArrayList<com.example.mealmate.model.ingrediantpojo.Meal> meals);
}
