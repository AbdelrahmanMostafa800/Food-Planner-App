package com.example.mealmate.homefragment.presenter;

import android.util.Log;

import com.example.mealmate.model.category.Category;
import com.example.mealmate.model.meal.Meal;
import com.example.mealmate.model.mealdatarepo.DataReposatoryImp;
import com.example.mealmate.model.mealdatarepo.DataReposatoryInterface;
import com.example.mealmate.homefragment.view.HomeFragmentView;
import com.example.mealmate.network.HomeNetworkCallback;

import java.util.ArrayList;

public class HomeFragmentPresenterImp implements HomeNetworkCallback, HomeFragmentPresenter {
    HomeFragmentView view;
    DataReposatoryInterface reposatory;
    public HomeFragmentPresenterImp(HomeFragmentView view) {
        this.view = view;
        this.reposatory= DataReposatoryImp.getInstance();
    }
    @Override
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
    public void onSuccessResult(ArrayList meal) {
        view.showMeal((Meal) meal.get(0));
    }

    @Override
    public void onFailureResult(String errorMsg) {
        Log.d("Failur", "onFailureResult: "+errorMsg);
    }

    @Override
    public void onRequestCategorySuccessResult(ArrayList<Category> categories) {
        view.showCategories(categories);
    }

    @Override
    public void onRequestIngrediantSuccessResult(ArrayList<com.example.mealmate.model.ingrediantpojo.Meal> meals) {
        view.showIngrediants(meals);
    }

}
