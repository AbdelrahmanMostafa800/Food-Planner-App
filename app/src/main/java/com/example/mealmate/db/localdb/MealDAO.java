package com.example.mealmate.db.localdb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mealmate.model.MealDb;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Completable;

@Dao
public interface MealDAO {
    @Insert
    Completable insertProduct(MealDb mealDb);
    @Query("SELECT * FROM mealtable WHERE userName = :userName")
    Flowable<List<MealDb>> getFavorits(String userName);
    @Query("DELETE FROM mealtable WHERE userName = :userName AND idMeal = :idMeal")
    Completable deleteMealFromDb(String userName, String idMeal);
}
