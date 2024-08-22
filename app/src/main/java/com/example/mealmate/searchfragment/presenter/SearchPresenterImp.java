package com.example.mealmate.searchfragment.presenter;


import android.util.Log;

import com.example.mealmate.model.category.Category;
import com.example.mealmate.model.category.CategoryList;
import com.example.mealmate.model.meal.Meal;
import com.example.mealmate.model.meal.MealList;
import com.example.mealmate.model.mealdatarepo.DataReposatoryImp;
import com.example.mealmate.model.mealdatarepo.DataReposatoryInterface;
import com.example.mealmate.network.HomeNetworkCallback;
import com.example.mealmate.network.MealRemoteDataSourceInterface;
import com.example.mealmate.network.SearchFragmentNetworkCallBack;
import com.example.mealmate.network.ShowFilterChipNetworkCallBack;
import com.example.mealmate.searchfragment.view.SearchFragmentView;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenterImp implements  SearchPresenterInterface{
    SearchFragmentView view;
    DataReposatoryInterface dReposatory;
    MealRemoteDataSourceInterface mReposatory;
    public SearchPresenterImp(SearchFragmentView view) {
        this.view = view;
        this.dReposatory= DataReposatoryImp.getInstance();
    }
    @Override
    public void getCategories() {
        Observable<CategoryList> observable= dReposatory.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<CategoryList> observer=new Observer<CategoryList>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(CategoryList meal) {
            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {
                Log.d("retro", "onComplete: ");
            }
        };
        observable.subscribe(observer);
    }

    @Override
    public void getIngrediants() {
//        dReposatory.getIngrediants(this);
    }

    @Override
    public void getFilterByCategory(String a, String selectedItem) {
//        mReposatory.getFilterByCategory(this,a,selectedItem);
    }

    @Override
    public void getMealsByFirstLetter(String chatMealFilter) {
        Observable<MealList> observable= dReposatory.getMealsByFirstLetter(chatMealFilter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<MealList> observer=new Observer<MealList>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d("retro", "onSubscribe: ");
            }

            @Override
            public void onNext(MealList meal) {
                view.showMeals(meal.getMeals());
            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);

    }


}
