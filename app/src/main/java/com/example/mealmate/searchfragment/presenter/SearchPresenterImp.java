package com.example.mealmate.searchfragment.presenter;


import android.content.Context;
import android.util.Log;

import com.example.mealmate.MealDayTransfere;
import com.example.mealmate.MealTransfere;
import com.example.mealmate.db.localdb.LocalDbDataSource;
import com.example.mealmate.model.DayMealDb;
import com.example.mealmate.model.MealDb;
import com.example.mealmate.model.category.CategoryList;
import com.example.mealmate.model.dbreposatory.DbReposatory;
import com.example.mealmate.model.dbreposatory.DbReposatoryInterface;
import com.example.mealmate.model.filterbycategorypojo.CategoryByFilter;
import com.example.mealmate.model.filterbycategorypojo.Meal;
import com.example.mealmate.model.ingrediantpojo.IngrediantList;
import com.example.mealmate.model.meal.MealList;
import com.example.mealmate.model.mealdatarepo.DataReposatoryImp;
import com.example.mealmate.model.mealdatarepo.DataReposatoryInterface;
import com.example.mealmate.searchfragment.view.SearchFragment;
import com.example.mealmate.searchfragment.view.SearchFragmentView;

import java.util.ArrayList;
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
    DataReposatoryInterface reposatory;
    DbReposatoryInterface dbReposatory;
    Context context;
    public SearchPresenterImp(SearchFragmentView view,Context context) {
        this.view = view;
        this.reposatory = DataReposatoryImp.getInstance(context);
        this.dbReposatory= DbReposatory.getInstance(LocalDbDataSource.getInstance(context));
        this.context=context;
    }
    @Override
    public void getCategories() {
        Observable<CategoryList> observable= reposatory.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<CategoryList> observer=new Observer<CategoryList>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(CategoryList meal) {
                List<String> f= meal.getCategories().stream().map(country -> country.getStrCategory()).collect(Collectors.toList());
                view.showListInSpinner("c",f);
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
        Observable<IngrediantList> observable= reposatory.getIngrediants()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<IngrediantList> observer=new Observer<IngrediantList>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(IngrediantList meal) {
                List<String> f= meal.getMeals().stream().map(country -> country.getStrIngredient()).collect(Collectors.toList());
                view.showListInSpinner("i",f);
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
        Observable<MealList> observable= reposatory.getMealsByFirstLetter(chatMealFilter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<MealList> observer=new Observer<MealList>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d("retro", "onSubscribe: ");
            }

            @Override
            public void onNext(MealList meal) {
                if(meal.getMeals()!=null) {
                    List<com.example.mealmate.model.filterbycategorypojo.Meal> meals = meal.getMeals().stream()
                            .map(m -> new com.example.mealmate.model.filterbycategorypojo.Meal(m.getStrMeal(), m.getStrMealThumb(), m.getIdMeal()))
                            .collect(Collectors.toList());
                    ArrayList<Meal> mealsArrayList = new ArrayList<>(meals);
                    view.showMeals(mealsArrayList);
                }
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
    public void getFilterByCategory(String query, String selectedItem) {
        Observable<CategoryByFilter> observable= reposatory.getFilterByCategory(query,selectedItem)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<CategoryByFilter> observer=new Observer<CategoryByFilter>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(CategoryByFilter meal) {
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
    public void insertMealToFavorit(String mealId, Context context) {
        Observable<MealList> observable=reposatory.getMealDetails(mealId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<MealList> observer=new Observer<MealList>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(MealList meal) {
                MealTransfere.insertMealIntoDb( meal.getMeals().get(0),context)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<MealDb>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                // do nothing
                            }

                            @Override
                            public void onNext(MealDb mealDb) {
                                // Image download is complete, you can now use the MealDb object
                                dbReposatory.insertMeal(mealDb)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(() -> {
                                            Log.d("insert", "don ");
                                        }, throwable -> {
                                            Log.d("insert", "fail ");
                                        });

                                // Save the MealDb object to the database
                            }

                            @Override
                            public void onError(Throwable e) {
                                // handle error
                            }

                            @Override
                            public void onComplete() {
                                // observable has completed
                            }
                        });
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
    public void insertMealTocalender(String day, String mealId, SearchFragment searchFragment) {
        Observable<MealList> observable = reposatory.getMealDetails(mealId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<MealList> observer = new Observer<MealList>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(MealList meal) {
                MealDayTransfere.insertMealIntoDb(day,meal.getMeals().get(0), context)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<DayMealDb>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                // do nothing
                            }

                            @Override
                            public void onNext(DayMealDb mealDb) {
                                // Image download is complete, you can now use the MealDb object
                                dbReposatory.insertDayMeal(day,mealDb)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(() -> {
                                            Log.d("insert", "don ");
                                        }, throwable -> {
                                            Log.d("insert", "fail ");
                                        });

                                // Save the MealDb object to the database
                            }

                            @Override
                            public void onError(Throwable e) {
                                // handle error
                            }

                            @Override
                            public void onComplete() {
                                // observable has completed
                            }
                        });
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



