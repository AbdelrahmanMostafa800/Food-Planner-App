package com.example.mealmate.homefragment.presenter;

import android.util.Log;

import com.example.mealmate.model.category.CategoryList;
import com.example.mealmate.model.ingrediantpojo.IngrediantList;
import com.example.mealmate.model.meal.MealList;
import com.example.mealmate.model.mealdatarepo.DataReposatoryImp;
import com.example.mealmate.model.mealdatarepo.DataReposatoryInterface;
import com.example.mealmate.homefragment.view.HomeFragmentView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeFragmentPresenterImp implements HomeFragmentPresenter {
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
                view.showMeal(meal.getMeals().get(0));
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
    public void getCategories() {
        Observable<CategoryList> observable= reposatory.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<CategoryList> observer=new Observer<CategoryList>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d("retro", "onSubscribe: ");
            }

            @Override
            public void onNext(CategoryList meal) {
                Log.d("single meal", meal.getCategories().get(0).getStrCategory());
                view.showCategories(meal.getCategories());
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
        Observable<IngrediantList> observable=  reposatory.getIngrediants()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<IngrediantList> observer=new Observer<IngrediantList>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(IngrediantList meal) {
                view.showIngrediants(meal.getMeals());
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
