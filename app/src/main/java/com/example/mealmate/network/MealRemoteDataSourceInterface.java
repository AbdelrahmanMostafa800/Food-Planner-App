package com.example.mealmate.network;

import com.example.mealmate.homefragmentselectedchip.presenter.ShowFilterChippresenter;
import com.example.mealmate.model.category.CategoryList;
import com.example.mealmate.model.filterbycategorypojo.CategoryByFilter;
import com.example.mealmate.model.ingrediantpojo.IngrediantList;
import com.example.mealmate.model.meal.MealList;

import io.reactivex.rxjava3.core.Observable;

public interface MealRemoteDataSourceInterface {
    Observable<MealList> makeNetworkCallSingleMeal();

    Observable<CategoryList> makeNetworkCallCategory();

    Observable<IngrediantList> makeNetworkCallIngrediants();

    Observable<CategoryByFilter> getFilterByCategory(String query, String strCategory);

    Observable<MealList> getMealDetails(String idMeal);

    Observable<MealList>  makeNetworkCallGetMealsByFirstLetter(String chatMealFilter);
}
