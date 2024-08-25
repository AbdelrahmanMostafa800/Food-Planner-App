package com.example.mealmate.model.filterbycategorypojo;

import java.util.ArrayList;

public class CategoryByFilter {
    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    public ArrayList<Meal> meals;
}
