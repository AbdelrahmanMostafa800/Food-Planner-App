package com.example.mealmate.db.localdb;

import android.content.Context;

import com.example.mealmate.model.DayMealDb;
import com.example.mealmate.model.MealDb;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Completable;

public class LocalDbDataSource implements LocalDbDataSourceInterface{
    MealDAO dao;
    DayMealDAO dDao;

    static LocalDbDataSource instance=null;

    public LocalDbDataSource(Context context){
        dao= AppDataBase.getInstance(context).getMealDAO();
        dDao= DayDb.getInstance(context).getDayMealDbDAO();
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

    @Override
    public Flowable<List<MealDb>> getFavorits(String userName) {
        return dao.getFavorits(userName);
    }

    @Override
    public Completable deleteMealFromDb(String userName, String idMeal) {
      return dao.deleteMealFromDb(userName,idMeal);
    }

    @Override
    public Completable insertDayMeal(String day,DayMealDb mealDb) {
        return dDao.insertProduct(mealDb);
    }

    @Override
    public Flowable<List<DayMealDb>> getLocalMealPlane(String day, String userName) {
        return dDao.getMealPlaneByDay(userName,day);
    }

    @Override
    public Completable deleteDayMealFromDb(String day, String userName, String idMeal) {
        return dDao.deleteMealFromDb(day,userName,idMeal);
    }
    @Override
    public  Flowable<List<DayMealDb>> getMealPlaneByUserName(String userName){
        return dDao.getMealPlaneByUserName(userName);
    }
}
