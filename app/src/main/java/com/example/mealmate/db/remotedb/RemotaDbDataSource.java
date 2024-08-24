package com.example.mealmate.db.remotedb;

import com.example.mealmate.model.MealDb;

import java.util.List;

public interface RemotaDbDataSource {
    void saveMealToDb(List<MealDb> mealDbsList);
}
