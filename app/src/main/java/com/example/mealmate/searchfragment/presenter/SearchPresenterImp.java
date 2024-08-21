package com.example.mealmate.searchfragment.presenter;


import android.util.Log;

import com.example.mealmate.model.meal.Meal;
import com.example.mealmate.model.mealdatarepo.DataReposatoryImp;
import com.example.mealmate.model.mealdatarepo.DataReposatoryInterface;
import com.example.mealmate.network.SearchFragmentNetworkCallBack;
import com.example.mealmate.searchfragment.view.SearchFragmentView;

import java.util.ArrayList;

public class SearchPresenterImp implements SearchFragmentNetworkCallBack, SearchPresenterInterface{
    SearchFragmentView view;
    DataReposatoryInterface reposatory;
    public SearchPresenterImp(SearchFragmentView view) {
        this.view = view;
        this.reposatory= DataReposatoryImp.getInstance();
    }

    @Override
    public void getMealsByFirstLetter(String chatMealFilter) {
        reposatory.getMealsByFirstLetter(this,chatMealFilter);
    }

    @Override
    public void onSuccessResultOfgetMealsByFirstLetter(ArrayList<Meal> meals) {
        view.showMeals(meals);
    }

    @Override
    public void onFailureResult(String message) {
        Log.d("onFailureResult", message);
    }
}
