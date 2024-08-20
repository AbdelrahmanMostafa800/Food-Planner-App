package com.example.mealmate.homefragmentselectedchip.presenter;

import android.util.Log;

import com.example.mealmate.homefragmentselectedchip.view.ShowFilterChipActivityView;
import com.example.mealmate.model.filterbycategorypojo.Meal;
import com.example.mealmate.network.MealRemoteDataSourceImp;
import com.example.mealmate.network.MealRemoteDataSourceInterface;
import com.example.mealmate.network.ShowFilterChipNetworkCallBack;

import java.util.ArrayList;

public class ShowFilterChippresenter implements ShowFilterChipNetworkCallBack, com.example.mealmate.homefragmentselectedchip.presenter.ShowFilterChippresenterInterface {
   ShowFilterChipActivityView view;
    MealRemoteDataSourceInterface reposatory;

    public ShowFilterChippresenter(ShowFilterChipActivityView view){
        this.view=view;
        this.reposatory= MealRemoteDataSourceImp.getInstance();
    }

    @Override
    public void getFilterByCategory(String query,String strCategory) {
        reposatory.getFilterByCategory(this,query,strCategory);
    }

    @Override
    public void onRequestgetFilterByCategory(ArrayList<Meal> meals) {
        Log.d("onRequestgetFilterByCategory", "onRequestgetFilterByCategory: "+meals.get(0).getStrMeal());
       view.showFilterByCategory(meals);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        Log.d("Filter", "onFailureResult: "+errorMsg);
    }
}
