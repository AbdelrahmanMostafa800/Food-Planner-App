package com.example.mealmate.network;

import com.example.mealmate.homefragmentselectedchip.presenter.ShowFilterChippresenter;
import com.example.mealmate.model.category.CategoryList;
import com.example.mealmate.model.ingrediantpojo.IngrediantList;
import com.example.mealmate.model.meal.MealList;

import io.reactivex.rxjava3.core.Observable;

public interface MealRemoteDataSourceInterface {
    Observable<MealList> makeNetworkCallSingleMeal();

    Observable<CategoryList> makeNetworkCallCategory();

    Observable<IngrediantList> makeNetworkCallIngrediants();

    void getFilterByCategory(ShowFilterChipNetworkCallBack networkCallback,String query, String strCategory);

    void getMealDetails(MealDetailsNetworkCallBack MealDetailspresenter, String idMeal);

    void makeNetworkCallGetMealsByFirstLetter(SearchFragmentNetworkCallBack networkCallback, String chatMealFilter);
}
