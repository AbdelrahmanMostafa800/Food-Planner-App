package com.example.mealmate.model.dbreposatory;

import com.example.mealmate.model.MealDb;

import io.reactivex.rxjava3.core.Completable;

public interface DbReposatoryInterface {
    Completable insertMeal(MealDb mealDb);
}
