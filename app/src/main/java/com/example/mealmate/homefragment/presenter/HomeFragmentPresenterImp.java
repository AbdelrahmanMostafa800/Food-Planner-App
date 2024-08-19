package com.example.mealmate.homefragment.presenter;

import android.content.Context;
import android.util.Log;

import com.example.mealmate.model.Category;
import com.example.mealmate.model.CategoryList;
import com.example.mealmate.model.Meal;
import com.example.mealmate.model.mealdatarepo.DataReposatoryImp;
import com.example.mealmate.model.mealdatarepo.DataReposatoryInterface;
import com.example.mealmate.homefragment.view.HomeFragmentView;
import com.example.mealmate.network.NetworkCallback;

import java.util.ArrayList;

public class HomeFragmentPresenterImp implements NetworkCallback, HomeFragmentPresenter {
    HomeFragmentView view;
    DataReposatoryInterface reposatory;
    public HomeFragmentPresenterImp(HomeFragmentView view) {
        this.view = view;
        this.reposatory= DataReposatoryImp.getInstance();
    }
    public void getSingleMeal(){
        reposatory.getSingleMeal(this);
    }

    @Override
    public void getCategories() {
        reposatory.getCategories(this);
    }

    @Override
    public void getIngrediants() {
        reposatory.getIngrediants(this);
    }


    @Override
    public void onSuccessResult(Meal meal) {
       view.showMeal(meal);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        Log.d("Failur", "onFailureResult: "+errorMsg);
    }

    @Override
    public void onRequestCategorySuccessResult(ArrayList<Category> category) {
       view.showCategories(category);
    }

    @Override
    public void onRequestIngrediantSuccessResult(ArrayList<com.example.mealmate.model.ingrediantpojo.Meal> meals) {
        view.showIngrediants(meals);
    }
}
