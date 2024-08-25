package com.example.mealmate.mealdetails.presenter;

import android.content.Context;

import com.example.mealmate.mealdetails.view.MealDetailsActivityView;
import com.example.mealmate.model.meal.MealList;
import com.example.mealmate.model.mealdatarepo.DataReposatoryImp;
import com.example.mealmate.model.mealdatarepo.DataReposatoryInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealDetailsPresenterImp implements MealDetailsPresenterInterface {
    MealDetailsActivityView view;
    DataReposatoryInterface reposatory;
    Context context;
    public MealDetailsPresenterImp(MealDetailsActivityView view, Context context) {
        this.view = view;
        this.context=context;
        this.reposatory= DataReposatoryImp.getInstance(context);
    }

    @Override
    public void getMealDetails(String idMeal) {
        Observable<MealList> observable=reposatory.getMealDetails(idMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<MealList> observer=new Observer<MealList>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(MealList meal) {
                view.showMealDetails(meal.getMeals().get(0),null);
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
