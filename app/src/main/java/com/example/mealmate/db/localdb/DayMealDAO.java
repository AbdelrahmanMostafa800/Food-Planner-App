package com.example.mealmate.db.localdb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mealmate.model.DayMealDb;
import com.example.mealmate.model.MealDb;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mealmate.model.MealDb;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Completable;

@Dao
public interface DayMealDAO {
    @Insert
    Completable insertProduct(DayMealDb mealDb);
    @Query("SELECT * FROM daytable WHERE userName = :userName")
    Flowable<List<DayMealDb>> getMealPlaneByUserName(String userName);
    @Query("SELECT * FROM daytable WHERE userName = :userName AND day = :Day")
    Flowable<List<DayMealDb>> getMealPlaneByDay(String userName,String Day);
    @Query("DELETE FROM daytable WHERE userName = :userName AND idMeal = :idMeal AND day = :day")
    Completable deleteMealFromDb(String day,String userName, String idMeal);
}
