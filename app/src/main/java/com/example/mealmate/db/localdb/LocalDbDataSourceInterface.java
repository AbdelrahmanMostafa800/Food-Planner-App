package com.example.mealmate.db.localdb;

import com.example.mealmate.model.MealDb;

import io.reactivex.rxjava3.core.Completable;

public interface LocalDbDataSourceInterface {
    Completable insertMeal(MealDb mealDb);
}
