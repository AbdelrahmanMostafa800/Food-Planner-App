package com.example.mealmate.model.mealdatarepo;

import com.example.mealmate.mealdetails.presenter.MealDetailsPresenterImp;
import com.example.mealmate.model.meal.MealList;
import com.example.mealmate.network.HomeNetworkCallback;
import com.example.mealmate.network.SearchFragmentNetworkCallBack;
import com.example.mealmate.searchfragment.presenter.SearchPresenterImp;

import io.reactivex.rxjava3.core.Observable;

public interface DataReposatoryInterface {
    Observable<MealList> getSingleMeal();

    void getCategories(HomeNetworkCallback networkCallback);

    void getIngrediants(HomeNetworkCallback networkCallback);

    void getMealDetails(MealDetailsPresenterImp mealDetailsPresenterImp, String idMeal);

    void getMealsByFirstLetter(SearchFragmentNetworkCallBack searchPresenterImp, String chatMealFilter);
}
