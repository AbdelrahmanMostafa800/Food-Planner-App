package com.example.mealmate.mealplane.presenter;

import com.example.mealmate.model.DayMealDb;
import com.example.mealmate.model.MealDb;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface MealPlaneFragmentPresenterInterface {
    Flowable<List<DayMealDb>> getLocalMealPlane(String day,String userName);

    Completable deleteDayMealFromDb(String day, String userName, String idMeal);

    String getUserID();

    String getUserStatus();

    Flowable<List<DayMealDb>> getAllLocalMealPlane(String userID);

    void saveToFirebaseDb(List<DayMealDb> mealDbsList);
}
