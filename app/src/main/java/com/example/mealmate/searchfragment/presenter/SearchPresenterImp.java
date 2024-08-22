package com.example.mealmate.searchfragment.presenter;


import android.util.Log;

import com.example.mealmate.model.category.Category;
import com.example.mealmate.model.meal.Meal;
import com.example.mealmate.model.mealdatarepo.DataReposatoryImp;
import com.example.mealmate.model.mealdatarepo.DataReposatoryInterface;
import com.example.mealmate.network.HomeNetworkCallback;
import com.example.mealmate.network.MealRemoteDataSourceInterface;
import com.example.mealmate.network.SearchFragmentNetworkCallBack;
import com.example.mealmate.network.ShowFilterChipNetworkCallBack;
import com.example.mealmate.searchfragment.view.SearchFragmentView;

import java.util.ArrayList;

public class SearchPresenterImp implements SearchFragmentNetworkCallBack, ShowFilterChipNetworkCallBack,HomeNetworkCallback,SearchPresenterInterface{
    SearchFragmentView view;
    DataReposatoryInterface dReposatory;
    MealRemoteDataSourceInterface mReposatory;
    public SearchPresenterImp(SearchFragmentView view) {
        this.view = view;
        this.dReposatory= DataReposatoryImp.getInstance();
    }
    @Override
    public void getCategories() {
        dReposatory.getCategories(this);
    }

    @Override
    public void getIngrediants() {
        dReposatory.getIngrediants(this);
    }

    @Override
    public void getFilterByCategory(String a, String selectedItem) {
        mReposatory.getFilterByCategory(this,a,selectedItem);
    }

    @Override
    public void getMealsByFirstLetter(String chatMealFilter) {
        dReposatory.getMealsByFirstLetter(this,chatMealFilter);
    }

    @Override
    public void onSuccessResultOfgetMealsByFirstLetter(ArrayList<Meal> meals) {
        view.showMeals(meals);
    }

    @Override
    public <T> void onSuccessResult(ArrayList<T> meal) {

    }

    @Override
    public void onRequestgetFilterByCategory(ArrayList<com.example.mealmate.model.filterbycategorypojo.Meal> meals) {
    }

    @Override
    public void onFailureResult(String message) {
        Log.d("onFailureResult", message);
    }

    @Override
    public void onRequestCategorySuccessResult(ArrayList<Category> categories) {

    }

    @Override
    public void onRequestIngrediantSuccessResult(ArrayList<com.example.mealmate.model.ingrediantpojo.Meal> meals) {

    }
}
