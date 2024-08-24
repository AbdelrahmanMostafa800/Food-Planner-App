package com.example.mealmate.mealplane.presenter;

import com.example.mealmate.model.DayMealDb;
import com.example.mealmate.model.MealDb;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface MealPlaneFragmentPresenterInterface {
    Flowable<List<DayMealDb>> getLocalMealPlane(String day,String userName);
}
