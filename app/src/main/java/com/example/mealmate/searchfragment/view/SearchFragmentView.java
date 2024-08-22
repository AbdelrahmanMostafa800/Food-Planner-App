package com.example.mealmate.searchfragment.view;

import com.example.mealmate.model.meal.Meal;

import java.util.ArrayList;
import java.util.List;

public interface SearchFragmentView {
    void showMeals(ArrayList<Meal> meals);

    void showListInSpinner(List<String> f);
}
