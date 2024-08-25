package com.example.mealmate.homefragmentselectedchip.presenter;

import android.content.Context;
import android.util.Log;

import com.example.mealmate.MealDayTransfere;
import com.example.mealmate.MealTransfere;
import com.example.mealmate.db.localdb.LocalDbDataSource;
import com.example.mealmate.homefragmentselectedchip.view.ShowFilterChipActivity;
import com.example.mealmate.homefragmentselectedchip.view.ShowFilterChipActivityView;
import com.example.mealmate.model.DayMealDb;
import com.example.mealmate.model.MealDb;
import com.example.mealmate.model.dbreposatory.DbReposatory;
import com.example.mealmate.model.dbreposatory.DbReposatoryInterface;
import com.example.mealmate.model.filterbycategorypojo.CategoryByFilter;
import com.example.mealmate.model.meal.Meal;
import com.example.mealmate.model.meal.MealList;
import com.example.mealmate.model.mealdatarepo.DataReposatoryImp;
import com.example.mealmate.model.mealdatarepo.DataReposatoryInterface;
import com.example.mealmate.network.MealRemoteDataSourceImp;
import com.example.mealmate.network.MealRemoteDataSourceInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ShowFilterChippresenter implements com.example.mealmate.homefragmentselectedchip.presenter.ShowFilterChippresenterInterface {
   ShowFilterChipActivityView view;
    DataReposatoryInterface reposatory;
    DbReposatoryInterface dbReposatory;
    Context context;

    public ShowFilterChippresenter(ShowFilterChipActivityView view, Context context){
        this.view=view;
        this.context=context;
        this.reposatory= DataReposatoryImp.getInstance(context);
        this.dbReposatory= DbReposatory.getInstance(LocalDbDataSource.getInstance(context));
        this.context=context;
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
                view.showMessage(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }

    @Override
    public void getMealById(String mealId, Context context) {
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
                                dbReposatory.insertMeal(mealDb)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(() -> {
                                            view.showMessage("Meal is added to favorits");
                                            Log.d("insert", "don ");
                                        }, throwable -> {
                                            view.showMessage("Meal already exist");
//
                                        });

                                // Save the MealDb object to the database
                            }

                            @Override
                            public void onError(Throwable e) {
                                view.showMessage(e.getMessage());
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
    public void insertMealTocalender(String day, String mealId, ShowFilterChipActivity showFilterChipActivity) {
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
                                           view.showMessage("Meal is added to meal plane");
                                        }, throwable -> {
                                            view.showMessage("can't add meal to meal plane");
                                        });

                                // Save the MealDb object to the database
                            }

                            @Override
                            public void onError(Throwable e) {
                                view.showMessage(e.getMessage());
                            }

                            @Override
                            public void onComplete() {
                                // observable has completed
                            }
                        });
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.showMessage(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);

    }
}
