package com.example.mealmate.network;

import com.example.mealmate.model.filterbycategorypojo.Meal;

import java.util.ArrayList;

public interface ShowFilterChipNetworkCallBack {
    void onRequestgetFilterByCategory(ArrayList<Meal> meals);
    void onFailureResult(String errorMsg);

}
