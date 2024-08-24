package com.example.mealmate.mealplane.presenter;

import android.content.Context;

import com.example.mealmate.db.localdb.LocalDbDataSource;
import com.example.mealmate.mealplane.view.MealPlaneFragment;
import com.example.mealmate.mealplane.view.MealPlaneFragmentView;
import com.example.mealmate.model.DayMealDb;
import com.example.mealmate.model.MealDb;
import com.example.mealmate.model.dbreposatory.DbReposatory;
import com.example.mealmate.model.dbreposatory.DbReposatoryInterface;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class MealPlaneFragmentPresenter implements MealPlaneFragmentPresenterInterface{
    MealPlaneFragmentView mealPlaneFragment;
    DbReposatoryInterface dbReposatory;
    Context context;
    public MealPlaneFragmentPresenter(MealPlaneFragmentView mealPlaneFragment, Context context) {
        this.mealPlaneFragment=mealPlaneFragment;
        this.context=context;
        this.dbReposatory= DbReposatory.getInstance(LocalDbDataSource.getInstance(context));
    }

    @Override
    public Flowable<List<DayMealDb>> getLocalMealPlane(String day,String userName) {
        return dbReposatory.getLocalMealPlane(day,userName);
    }
}
