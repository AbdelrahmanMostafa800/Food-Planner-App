package com.example.mealmate.mealdetails.presenter;

import android.util.Log;

import com.example.mealmate.mealdetails.view.MealDetailsActivityView;
import com.example.mealmate.model.meal.Meal;
import com.example.mealmate.model.mealdatarepo.DataReposatoryImp;
import com.example.mealmate.model.mealdatarepo.DataReposatoryInterface;
import com.example.mealmate.network.MealDetailsNetworkCallBack;

public class MealDetailsPresenterImp implements MealDetailsPresenterInterface, MealDetailsNetworkCallBack {
    MealDetailsActivityView view;
    DataReposatoryInterface reposatory;
    public MealDetailsPresenterImp(MealDetailsActivityView view) {
        this.view = view;
        this.reposatory= DataReposatoryImp.getInstance();
    }

    @Override
    public void getMealDetails(String idMeal) {
        reposatory.getMealDetails(this,idMeal);
    }
    @Override
    public void onSuccessResultOfgetMealDetails(Meal meal) {
        view.showMealDetails(meal);
    }
    @Override
    public void onFailureResult(String message) {
        Log.d("TAG", "onFailureResult: "+message);
    }
}
