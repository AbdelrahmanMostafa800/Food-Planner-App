package com.example.mealmate.model.mealdatarepo;

import com.example.mealmate.mealdetails.presenter.MealDetailsPresenterImp;
import com.example.mealmate.network.HomeNetworkCallback;
import com.example.mealmate.network.SearchFragmentNetworkCallBack;
import com.example.mealmate.searchfragment.presenter.SearchPresenterImp;

public interface DataReposatoryInterface {
    void getSingleMeal(HomeNetworkCallback networkCallback);

    void getCategories(HomeNetworkCallback networkCallback);

    void getIngrediants(HomeNetworkCallback networkCallback);

    void getMealDetails(MealDetailsPresenterImp mealDetailsPresenterImp, String idMeal);

    void getMealsByFirstLetter(SearchFragmentNetworkCallBack searchPresenterImp, String chatMealFilter);
}
