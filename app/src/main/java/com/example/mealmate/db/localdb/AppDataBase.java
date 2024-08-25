package com.example.mealmate.db.localdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mealmate.model.MealDb;


@Database(entities = {MealDb.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance = null;

    public abstract MealDAO getMealDAO();

    public static synchronized AppDataBase getInstance(Context context) {
        if (instance == null) {
                instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "meal").build();
         }
        return instance;
    }
}
