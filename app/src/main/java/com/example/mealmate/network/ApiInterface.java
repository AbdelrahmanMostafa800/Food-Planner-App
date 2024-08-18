package com.example.mealmate.network;


import android.provider.DocumentsContract;

import com.example.mealmate.model.Meal;
import com.example.mealmate.model.MealList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("api/json/v1/1/random.php")
     Call<MealList> getSingleMeal();
    @GET("api/json/v1/1/categories.php")
    Call<MealList> getAllCategories();
}
