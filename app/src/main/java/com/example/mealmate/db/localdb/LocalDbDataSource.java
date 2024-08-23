package com.example.mealmate.db.localdb;

import android.content.Context;

import com.example.mealmate.model.MealDb;

public class LocalDbDataSource implements LocalDbDataSourceInterface{
    MealDAO dao;

    static LocalDbDataSource instance=null;

    public LocalDbDataSource(Context context){
        dao= AppDataBase.getInstance(context).getMealDAO();
    }
    public static synchronized LocalDbDataSource getInstance(Context context) {
        if (instance == null) {
            instance = new LocalDbDataSource(context);
        }
        return instance;
    }

    @Override
    public void insertMeal(MealDb mealDb) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.insertProduct(mealDb);
            }
        }).start();
    }
}
