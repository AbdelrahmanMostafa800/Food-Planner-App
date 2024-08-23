package com.example.mealmate.model.dbreposatory;

import com.example.mealmate.db.localdb.LocalDbDataSource;
import com.example.mealmate.db.localdb.LocalDbDataSourceInterface;
import com.example.mealmate.model.MealDb;

import io.reactivex.rxjava3.core.Completable;

public class DbReposatory implements DbReposatoryInterface{
    private LocalDbDataSourceInterface localDbDataSource;
    private static DbReposatory instance=null;
    public DbReposatory(LocalDbDataSourceInterface localDbDataSource) {
        this.localDbDataSource=localDbDataSource;
    }
    public static synchronized DbReposatory getInstance(LocalDbDataSourceInterface localDbDataSource) {
        if (instance == null) {
            instance = new DbReposatory(localDbDataSource);
        }
        return instance;
    }

    @Override
    public Completable insertMeal(MealDb mealDb) {
        return localDbDataSource.insertMeal(mealDb);
//        localDbDataSource.insertMeal(mealDb);
    }
}
