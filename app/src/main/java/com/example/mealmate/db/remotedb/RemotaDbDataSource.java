package com.example.mealmate.db.remotedb;

import com.example.mealmate.model.DayMealDb;
import com.example.mealmate.model.MealDb;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface RemotaDbDataSource {
    Completable saveMealToDb(List<MealDb> mealDbsList);

    Single<List<MealDb>> retrieveMealsFromFirestore(String userId);

    void saveDayMealToFirestore(List<DayMealDb> dayMealDb);

    Single<List<DayMealDb>> retrieveDayMealsFromFirestore(String userId);
}
