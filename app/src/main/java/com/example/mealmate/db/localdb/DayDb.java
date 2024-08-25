package com.example.mealmate.db.localdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mealmate.model.DayMealDb;
import com.example.mealmate.model.MealDb;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mealmate.model.MealDb;

@Database(entities = {DayMealDb.class}, version = 1)
public abstract class DayDb extends RoomDatabase {
    private static DayDb instance = null;

    public abstract DayMealDAO getDayMealDbDAO();

    public static synchronized DayDb getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), DayDb.class, "Day").build();
        }
        return instance;
    }
}