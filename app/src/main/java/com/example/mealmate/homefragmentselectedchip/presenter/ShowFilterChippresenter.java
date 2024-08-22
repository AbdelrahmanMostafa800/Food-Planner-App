package com.example.mealmate.homefragmentselectedchip.presenter;

import android.util.Log;

import com.example.mealmate.homefragmentselectedchip.view.ShowFilterChipActivityView;
import com.example.mealmate.model.filterbycategorypojo.CategoryByFilter;
import com.example.mealmate.model.filterbycategorypojo.Meal;
import com.example.mealmate.model.meal.MealList;
import com.example.mealmate.network.MealRemoteDataSourceImp;
import com.example.mealmate.network.MealRemoteDataSourceInterface;
import com.example.mealmate.network.ShowFilterChipNetworkCallBack;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ShowFilterChippresenter implements ShowFilterChipNetworkCallBack, com.example.mealmate.homefragmentselectedchip.presenter.ShowFilterChippresenterInterface {
   ShowFilterChipActivityView view;
    MealRemoteDataSourceInterface reposatory;

    public ShowFilterChippresenter(ShowFilterChipActivityView view){
        this.view=view;
        this.reposatory= MealRemoteDataSourceImp.getInstance();
    }

    @Override
    public void getFilterByCategory(String query,String strCategory) {
        Observable<CategoryByFilter> observable= reposatory.getFilterByCategory(query,strCategory)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<CategoryByFilter> observer=new Observer<CategoryByFilter>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(CategoryByFilter meal) {
                view.showFilterByCategory(meal.getMeals());
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
