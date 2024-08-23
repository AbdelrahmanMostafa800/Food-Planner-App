package com.example.mealmate.homefragment.presenter;

import com.example.mealmate.model.MealDb;

public interface HomeFragmentPresenter {
     void getSingleMeal();

    void getCategories();

    void getIngrediants();

    void insertMeal(MealDb mealDb);
}
