package com.example.mealmate.searchfragment.presenter;


import android.util.Log;

import com.example.mealmate.model.category.CategoryList;
import com.example.mealmate.model.ingrediantpojo.IngrediantList;
import com.example.mealmate.model.meal.MealList;
import com.example.mealmate.model.mealdatarepo.DataReposatoryImp;
import com.example.mealmate.model.mealdatarepo.DataReposatoryInterface;
import com.example.mealmate.searchfragment.view.SearchFragmentView;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenterImp implements  SearchPresenterInterface{
    SearchFragmentView view;
    DataReposatoryInterface Reposatory;
    public SearchPresenterImp(SearchFragmentView view) {
        this.view = view;
        this.Reposatory = DataReposatoryImp.getInstance();
    }
    @Override
    public void getCategories() {
        Observable<CategoryList> observable= Reposatory.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<CategoryList> observer=new Observer<CategoryList>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(CategoryList meal) {
                List<String> f= meal.getCategories().stream().map(country -> country.getStrCategory()).collect(Collectors.toList());
                view.showListInSpinner(f);
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
        Observable<IngrediantList> observable= Reposatory.getIngrediants()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<IngrediantList> observer=new Observer<IngrediantList>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(IngrediantList meal) {
                List<String> f= meal.getMeals().stream().map(country -> country.getStrIngredient()).collect(Collectors.toList());
                view.showListInSpinner(f);
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
    public void getMealsByFirstLetter(String chatMealFilter) {
        Observable<MealList> observable= Reposatory.getMealsByFirstLetter(chatMealFilter)
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

    @Override
    public void getFilterByCategory(String a, String selectedItem) {
//        mReposatory.getFilterByCategory(this,a,selectedItem);
    }


}
