package com.example.mealmate.db.localdb;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.mealmate.model.MealDb;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface MealDAO {
    @Insert
    Completable insertProduct(MealDb mealDb);
}
