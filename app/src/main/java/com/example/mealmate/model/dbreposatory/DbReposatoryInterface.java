package com.example.mealmate.model.dbreposatory;

import com.example.mealmate.model.DayMealDb;
import com.example.mealmate.model.MealDb;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface DbReposatoryInterface {
    Completable insertMeal(MealDb mealDb);

    Flowable<List<MealDb>> getFavorits(String userName);

    Completable deleteMealFromDb(String userName, String idMeal);

    void saveToFirebaseDb(List<MealDb> mealDbsList);

    Completable insertDayMeal(String day,DayMealDb mealDb);

    Flowable<List<DayMealDb>> getLocalMealPlane(String day, String userName);

    Completable deleteDayMealFromDb(String day, String userName, String idMeal);

    Single<List<MealDb>> retrieveMealsFromFirestore(String userId);
}
