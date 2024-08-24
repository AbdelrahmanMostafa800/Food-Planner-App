package com.example.mealmate.model.dbreposatory;

import com.example.mealmate.db.localdb.LocalDbDataSourceInterface;
import com.example.mealmate.db.remotedb.RemotaDbDataSource;
import com.example.mealmate.db.remotedb.RemotaDbDataSourceImp;
import com.example.mealmate.model.DayMealDb;
import com.example.mealmate.model.MealDb;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class DbReposatory implements DbReposatoryInterface{
    private LocalDbDataSourceInterface localDbDataSource;
    RemotaDbDataSource remoteDbDataSource;
    private static DbReposatory instance=null;
    public DbReposatory(LocalDbDataSourceInterface localDbDataSource) {
        this.localDbDataSource=localDbDataSource;
        remoteDbDataSource= new RemotaDbDataSourceImp();
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
    }

    @Override
    public Flowable<List<MealDb>> getFavorits(String userName) {
        return localDbDataSource.getFavorits(userName);
    }

    @Override
    public Completable deleteMealFromDb(String userName, String idMeal) {
      return  localDbDataSource.deleteMealFromDb(userName,idMeal);
    }
    @Override
    public void saveToFirebaseDb(List<MealDb> mealDbsList){
        remoteDbDataSource.saveMealToDb(mealDbsList);
    }

    @Override
    public Completable insertDayMeal(String day,DayMealDb mealDb) {
        return localDbDataSource.insertDayMeal(day,mealDb);
    }

    @Override
    public Flowable<List<DayMealDb>> getLocalMealPlane(String day, String userName) {
        return localDbDataSource.getLocalMealPlane(day,userName);
    }

    @Override
    public Completable deleteDayMealFromDb(String day, String userName, String idMeal) {
        return localDbDataSource.deleteDayMealFromDb(day,userName,idMeal);
    }

    @Override
    public Single<List<MealDb>> retrieveMealsFromFirestore(String userId) {
        return remoteDbDataSource.retrieveMealsFromFirestore(userId);
    }

    @Override
    public Single<List<DayMealDb>> retrieveDayMealsFromFirestore(String userId) {
        return  remoteDbDataSource.retrieveDayMealsFromFirestore(userId);
    }
    @Override
    public void saveToDAyMealFirebaseDb(List<DayMealDb> mealDbsList){
        remoteDbDataSource.saveDayMealToFirestore(mealDbsList);
    }

    @Override
    public Flowable<List<DayMealDb>> getAllLocalMealPlane(String userID) {
        return localDbDataSource.getMealPlaneByUserName(userID);
    }
}
