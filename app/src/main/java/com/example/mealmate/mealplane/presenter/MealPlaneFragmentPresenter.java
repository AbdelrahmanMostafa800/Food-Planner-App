package com.example.mealmate.mealplane.presenter;

import android.content.Context;

import com.example.mealmate.db.localdb.LocalDbDataSource;
import com.example.mealmate.mealplane.view.MealPlaneFragment;
import com.example.mealmate.mealplane.view.MealPlaneFragmentView;
import com.example.mealmate.model.DayMealDb;
import com.example.mealmate.model.MealDb;
import com.example.mealmate.model.dbreposatory.DbReposatory;
import com.example.mealmate.model.dbreposatory.DbReposatoryInterface;
import com.example.mealmate.model.userrepo.UserAuthReposatoryImp;
import com.example.mealmate.model.userrepo.UserAuthReposatoryInterface;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class MealPlaneFragmentPresenter implements MealPlaneFragmentPresenterInterface{
    MealPlaneFragmentView mealPlaneFragment;
    DbReposatoryInterface dbReposatory;
    UserAuthReposatoryInterface auth;
    Context context;
    public MealPlaneFragmentPresenter(MealPlaneFragmentView mealPlaneFragment, Context context) {
        this.mealPlaneFragment=mealPlaneFragment;
        this.context=context;
        this.dbReposatory= DbReposatory.getInstance(LocalDbDataSource.getInstance(context));
        auth= UserAuthReposatoryImp.getInstance();
    }

    @Override
    public Flowable<List<DayMealDb>> getLocalMealPlane(String day,String userName) {
        return dbReposatory.getLocalMealPlane(day,userName);
    }

    @Override
    public Completable deleteDayMealFromDb(String day, String userName, String idMeal) {
        return dbReposatory.deleteDayMealFromDb(day,userName,idMeal);
    }

    @Override
    public String getUserID() {
        return auth.getUserId();
    }
}
