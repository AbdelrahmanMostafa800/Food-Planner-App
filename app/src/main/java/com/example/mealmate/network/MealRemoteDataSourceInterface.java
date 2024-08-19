package com.example.mealmate.network;

public interface MealRemoteDataSourceInterface {
    void makeNetworkCallSingleMeal(NetworkCallback networkCallback);

    void makeNetworkCallCategory(NetworkCallback networkCallback);

    void makeNetworkCallIngrediants(NetworkCallback networkCallback);
}
