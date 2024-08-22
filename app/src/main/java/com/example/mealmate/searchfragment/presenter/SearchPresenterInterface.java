package com.example.mealmate.searchfragment.presenter;

import com.example.mealmate.model.meal.Meal;

import java.util.ArrayList;

public interface SearchPresenterInterface {
    void getMealsByFirstLetter(String chatMealFilter);

    void getCategories();

    void getIngrediants();

    void getFilterByCategory(String a, String selectedItem);
}
