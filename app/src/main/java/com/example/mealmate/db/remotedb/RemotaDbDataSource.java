package com.example.mealmate.db.remotedb;

import com.example.mealmate.model.MealDb;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface RemotaDbDataSource {
    void saveMealToDb(List<MealDb> mealDbsList);

    Single<List<MealDb>> retrieveMealsFromFirestore(String userId);
}
