package com.example.mealmate.favoritsfragment.presenter;

import com.example.mealmate.model.MealDb;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface FavoritsPresenterInterface {
    Flowable<List<MealDb>> getLocalFavorits(String userName);

    Completable deleteMealFromDb(String userName, String idMeal);

    void saveToFirebaseDb(List<MealDb> mealDbsList);
}
