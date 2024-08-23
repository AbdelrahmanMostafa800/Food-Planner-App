package com.example.mealmate.db.localdb;

import android.content.Context;

import com.example.mealmate.model.MealDb;

import io.reactivex.rxjava3.core.Completable;

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
    public Completable insertMeal(MealDb mealDb) {
           return dao.insertProduct(mealDb);
    }
}
