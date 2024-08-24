package com.example.mealmate.homefragment.presenter;

import android.content.Context;
import android.util.Log;

import com.example.mealmate.db.localdb.LocalDbDataSource;
import com.example.mealmate.model.DayMealDb;
import com.example.mealmate.model.MealDb;
import com.example.mealmate.model.category.CategoryList;
import com.example.mealmate.model.dbreposatory.DbReposatory;
import com.example.mealmate.model.dbreposatory.DbReposatoryInterface;
import com.example.mealmate.model.ingrediantpojo.IngrediantList;
import com.example.mealmate.model.meal.MealList;
import com.example.mealmate.model.mealdatarepo.DataReposatoryImp;
import com.example.mealmate.model.mealdatarepo.DataReposatoryInterface;
import com.example.mealmate.homefragment.view.HomeFragmentView;
import com.example.mealmate.model.userrepo.UserAuthReposatoryImp;
import com.example.mealmate.model.userrepo.UserAuthReposatoryInterface;
import com.example.mealmate.model.userrepo.UserReposatoryImp;
import com.example.mealmate.model.userrepo.UserReposatoryInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeFragmentPresenterImp implements HomeFragmentPresenter {
    HomeFragmentView view;
    DataReposatoryInterface reposatory;
    DbReposatoryInterface dbReposatory;
    UserAuthReposatoryInterface userAuth;
    UserReposatoryInterface userReposatory;
    Context context;
    public HomeFragmentPresenterImp(HomeFragmentView view, Context context) {
        this.view = view;
        this.context=context;
        this.reposatory= DataReposatoryImp.getInstance(context);

        this.dbReposatory= DbReposatory.getInstance(LocalDbDataSource.getInstance(context));
        userAuth= UserAuthReposatoryImp.getInstance();
        userReposatory= UserReposatoryImp.getInstance(context);
    }
    @Override
    public void getSingleMeal(){
        String idMeal=userReposatory.getSavedMealId();
        Log.d("idMeal", "getSingleMeal: "+idMeal);
        if(idMeal==null) {
            Observable<MealList> observable = reposatory.getSingleMeal()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
            Observer<MealList> observer = new Observer<MealList>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    Log.d("retro", "onSubscribe: ");
                }

                @Override
                public void onNext(MealList meal) {
                    userReposatory.addMeal(meal.getMeals().get(0).getIdMeal());
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
        }else{
            Observable<MealList> observable=reposatory.getMealDetails(idMeal)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
            Observer<MealList> observer=new Observer<MealList>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {

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

    @Override
    public void insertMeal(MealDb mealDb) {
        dbReposatory.insertMeal(mealDb)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    Log.d("insert", "don ");
                }, throwable -> {
                    Log.d("insert", "fail ");
                });
    }

    @Override
    public void insertDayMeal(String day,DayMealDb mealDb) {
        Log.d("homeday", " insertDayMeal"+day);
        dbReposatory.insertDayMeal(day,mealDb)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    Log.d("insert", "don ");
                }, throwable -> {
                    Log.d("insert", "fail ");
                });
    }

    @Override
    public void retrieveMealsFromFirestore() {
        Log.d("userid", userAuth.getUserId());
        dbReposatory.retrieveMealsFromFirestore(userAuth.getUserId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealDbsList -> {
                    // do something with the list of meals
                    for (MealDb mealDb : mealDbsList) {
                        Log.d("MealCallback", "Received meal: " + mealDb.getStrMeal());
                        dbReposatory.insertMeal(mealDb)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(() -> {
                                    Log.d("insert", "don ");
                                }, throwable -> {
                                    Log.d("insert", "fail ");
                                });
                    }
                    Log.d("MealCallback", "Received meals: " + mealDbsList.size());
                }, throwable -> {
                    Log.w("MealCallback", "Error retrieving meals", throwable);
                });
    }

    @Override
    public String getUserStatus() {
        return userReposatory.getUserLoginStatus();
    }

    @Override
    public boolean loginOut() {
       return userReposatory.loginOut(context);
    }

    @Override
    public void retrieveDayMealsFromFirestore() {
        dbReposatory.retrieveDayMealsFromFirestore(userAuth.getUserId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealDbsList -> {
                    // do something with the list of meals
                    for (DayMealDb mealDb : mealDbsList) {
                        Log.d("MealCallback", "Received meal: " + mealDb.getStrMeal());
                        dbReposatory.insertDayMeal(mealDb.getDay(),mealDb)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(() -> {
                                    Log.d("insert", "don ");
                                }, throwable -> {
                                    Log.d("insert", "fail ");
                                });
                    }
                    Log.d("MealCallback", "Received meals: " + mealDbsList.size());
                }, throwable -> {
                    Log.w("MealCallback", "Error retrieving meals", throwable);
                });
    }
}
