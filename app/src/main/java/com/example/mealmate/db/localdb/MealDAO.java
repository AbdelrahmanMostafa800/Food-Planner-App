package com.example.mealmate.db.localdb;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.mealmate.model.MealDb;

@Dao
public interface MealDAO {
    @Insert
    void insertProduct(MealDb mealDb);
}
