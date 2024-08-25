package com.example.mealmate.mealplane.view;

import com.example.mealmate.model.DayMealDb;

public interface OnCalenderClickListener {
    void deleteMealFromDb(String day, String userName,String idMeal);

    void showMealDeatails(DayMealDb dayMealDb);
}
