package com.example.mealmate.searchfragment.view;

import com.example.mealmate.model.meal.Meal;

import java.util.ArrayList;
import java.util.List;

public interface SearchFragmentView {
    void showMeals(ArrayList<com.example.mealmate.model.filterbycategorypojo.Meal> meals);

    void showListInSpinner(String selectedChip,List<String> f);
}
