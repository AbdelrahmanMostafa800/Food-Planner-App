package com.example.mealmate.db.localdb;

import com.example.mealmate.model.DayMealDb;
import com.example.mealmate.model.MealDb;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Completable;

public interface LocalDbDataSourceInterface {
    Completable insertMeal(MealDb mealDb);

    Flowable<List<MealDb>> getFavorits(String userName);

    Completable deleteMealFromDb(String userName, String idMeal);

    Completable insertDayMeal(String day,DayMealDb mealDb);

    Flowable<List<DayMealDb>> getLocalMealPlane(String day, String userName);

    Completable deleteDayMealFromDb(String day, String userName, String idMeal);
}
