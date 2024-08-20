package com.example.mealmate.network;


import com.example.mealmate.model.CategoryList;
import com.example.mealmate.model.countriespojo.CountriesList;
import com.example.mealmate.model.MealList;
import com.example.mealmate.model.filterbycategorypojo.CategoryByFilter;
import com.example.mealmate.model.ingrediantpojo.IngrediantList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("api/json/v1/1/random.php")
    Call<MealList> getSingleMeal();

    @GET("api/json/v1/1/categories.php")
    Call<CategoryList> getAllCategories();
    @GET("api/json/v1/1/list.php?i=list")
    Call<IngrediantList> getAllIngrediant();
    @GET("api/json/v1/1/filter.php")
    Call<CategoryByFilter> getFilterByIngredient(@Query("i") String ingredient);

    @GET("api/json/v1/1/filter.php")
    Call<CategoryByFilter> getFilterByArea(@Query("a") String area);

    @GET("api/json/v1/1/filter.php")
    Call<CategoryByFilter> getFilterByCategory(@Query("c") String category);
}