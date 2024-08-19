package com.example.mealmate.model.ingrediantpojo;

import java.util.ArrayList;

public class IngrediantList {
    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    public ArrayList<Meal> meals;
}
