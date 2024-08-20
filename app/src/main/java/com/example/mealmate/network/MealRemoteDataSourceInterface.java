package com.example.mealmate.network;

public interface MealRemoteDataSourceInterface {
    void makeNetworkCallSingleMeal(HomeNetworkCallback networkCallback);

    void makeNetworkCallCategory(HomeNetworkCallback networkCallback);

    void makeNetworkCallIngrediants(HomeNetworkCallback networkCallback);

    void getFilterByCategory(ShowFilterChipNetworkCallBack networkCallback,String query, String strCategory);
}
