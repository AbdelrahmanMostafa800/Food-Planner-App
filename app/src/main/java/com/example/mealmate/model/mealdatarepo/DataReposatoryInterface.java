package com.example.mealmate.model.mealdatarepo;

import com.example.mealmate.homefragment.presenter.HomeFragmentPresenterImp;
import com.example.mealmate.network.NetworkCallback;

public interface DataReposatoryInterface {
    void getSingleMeal(NetworkCallback networkCallback);

    void getCategories(NetworkCallback networkCallback);

    void getIngrediants(NetworkCallback networkCallback);
}
