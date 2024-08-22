package com.example.mealmate.network;


import com.example.mealmate.model.category.CategoryList;
import com.example.mealmate.model.meal.MealList;
import com.example.mealmate.model.filterbycategorypojo.CategoryByFilter;
import com.example.mealmate.model.ingrediantpojo.IngrediantList;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("api/json/v1/1/random.php")
    Observable<MealList> getSingleMeal();

    @GET("api/json/v1/1/categories.php")
    Observable<CategoryList> getAllCategories();
    @GET("api/json/v1/1/list.php?i=list")
    Call<IngrediantList> getAllIngrediant();
    @GET("api/json/v1/1/filter.php")
    Call<CategoryByFilter> getFilterByIngredient(@Query("i") String ingredient);

    @GET("api/json/v1/1/filter.php")
    Call<CategoryByFilter> getFilterByArea(@Query("a") String area);

    @GET("api/json/v1/1/filter.php")
    Call<CategoryByFilter> getFilterByCategory(@Query("c") String category);
    @GET("api/json/v1/1/lookup.php")
    Call<MealList> getMealById(@Query("i") String idMeal);
    @GET("api/json/v1/1/search.php")
    Call<MealList> getMealsByFirstLetter(@Query("f") String chatMealFilter);
}