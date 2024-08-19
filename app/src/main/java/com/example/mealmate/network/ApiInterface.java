package com.example.mealmate.network;


import com.example.mealmate.model.CategoryList;
import com.example.mealmate.model.countriespojo.CountriesList;
import com.example.mealmate.model.MealList;
import com.example.mealmate.model.ingrediantpojo.IngrediantList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("api/json/v1/1/random.php")
    Call<MealList> getSingleMeal();

    @GET("api/json/v1/1/categories.php")
    Call<CategoryList> getAllCategories();
    @GET("api/json/v1/1/list.php?i=list")
    Call<IngrediantList> getAllIngrediant();
}