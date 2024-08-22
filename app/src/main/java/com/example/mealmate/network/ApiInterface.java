package com.example.mealmate.network;


import com.example.mealmate.model.category.CategoryList;
import com.example.mealmate.model.meal.MealList;
import com.example.mealmate.model.filterbycategorypojo.CategoryByFilter;
import com.example.mealmate.model.ingrediantpojo.IngrediantList;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {
    @GET("api/json/v1/1/random.php")
    Observable<MealList> getSingleMeal();

    @GET("api/json/v1/1/categories.php")
    Observable<CategoryList> getAllCategories();
    @GET("api/json/v1/1/list.php?i=list")
    Observable<IngrediantList> getAllIngrediant();
//
    @GET("api/json/v1/1/filter.php")
    Observable<CategoryByFilter> getFilterByParams(@QueryMap Map<String, String> params);

    @GET("api/json/v1/1/lookup.php")
    Observable<MealList> getMealById(@Query("i") String idMeal);
    @GET("api/json/v1/1/search.php")
    Call<MealList> getMealsByFirstLetter(@Query("f") String chatMealFilter);
}