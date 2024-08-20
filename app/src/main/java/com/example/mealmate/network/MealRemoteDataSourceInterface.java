package com.example.mealmate.network;

import com.example.mealmate.homefragmentselectedchip.presenter.ShowFilterChippresenter;

public interface MealRemoteDataSourceInterface {
    void makeNetworkCallSingleMeal(HomeNetworkCallback networkCallback);

    void makeNetworkCallCategory(HomeNetworkCallback networkCallback);

    void makeNetworkCallIngrediants(HomeNetworkCallback networkCallback);

    void getFilterByCategory(ShowFilterChipNetworkCallBack networkCallback,String query, String strCategory);

    void getMealDetails(MealDetailsNetworkCallBack MealDetailspresenter, String idMeal);
}
