package com.example.mealmate.favoritsfragment.view;

import com.example.mealmate.model.MealDb;

public interface OnFavClickListener {
    void deleteMealFromDb(String userName, String idMeal);

    void showMealDeatails(MealDb mealDb);
}
