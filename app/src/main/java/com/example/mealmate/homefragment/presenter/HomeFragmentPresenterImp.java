package com.example.mealmate.homefragment.presenter;

import android.util.Log;

import com.example.mealmate.model.category.Category;
import com.example.mealmate.model.meal.Meal;
import com.example.mealmate.model.meal.MealList;
import com.example.mealmate.model.mealdatarepo.DataReposatoryImp;
import com.example.mealmate.model.mealdatarepo.DataReposatoryInterface;
import com.example.mealmate.homefragment.view.HomeFragmentView;
import com.example.mealmate.network.HomeNetworkCallback;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeFragmentPresenterImp implements HomeNetworkCallback, HomeFragmentPresenter {
    HomeFragmentView view;
    DataReposatoryInterface reposatory;
    public HomeFragmentPresenterImp(HomeFragmentView view) {
        this.view = view;
        this.reposatory= DataReposatoryImp.getInstance();
    }
    @Override
    public void getSingleMeal(){
        Observable<MealList> observable=reposatory.getSingleMeal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<MealList> observer=new Observer<MealList>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d("retro", "onSubscribe: ");
            }

            @Override
            public void onNext(MealList meal) {
                Log.d("single meal", meal.getMeals().get(0).getStrMeal());
                view.showMeal(meal.getMeals().get(0));
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
