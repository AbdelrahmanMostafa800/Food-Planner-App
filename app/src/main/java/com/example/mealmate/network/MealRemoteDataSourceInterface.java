package com.example.mealmate.network;

import com.example.mealmate.homefragmentselectedchip.presenter.ShowFilterChippresenter;
import com.example.mealmate.model.meal.MealList;

import io.reactivex.rxjava3.core.Observable;

public interface MealRemoteDataSourceInterface {
    Observable<MealList> makeNetworkCallSingleMeal();

    void makeNetworkCallCategory(HomeNetworkCallback networkCallback);

    void makeNetworkCallIngrediants(HomeNetworkCallback networkCallback);

    void getFilterByCategory(ShowFilterChipNetworkCallBack networkCallback,String query, String strCategory);

    void getMealDetails(MealDetailsNetworkCallBack MealDetailspresenter, String idMeal);

    void makeNetworkCallGetMealsByFirstLetter(SearchFragmentNetworkCallBack networkCallback, String chatMealFilter);
}
