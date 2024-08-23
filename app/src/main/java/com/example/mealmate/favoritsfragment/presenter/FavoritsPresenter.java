package com.example.mealmate.favoritsfragment.presenter;

import android.content.Context;

import com.example.mealmate.db.localdb.LocalDbDataSource;
import com.example.mealmate.favoritsfragment.view.FavoritsFragmentView;
import com.example.mealmate.model.MealDb;
import com.example.mealmate.model.dbreposatory.DbReposatory;
import com.example.mealmate.model.dbreposatory.DbReposatoryInterface;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;


public class FavoritsPresenter implements FavoritsPresenterInterface{
    FavoritsFragmentView view;
    DbReposatoryInterface dbReposatory;
    Context context;
    public FavoritsPresenter(FavoritsFragmentView view, Context context) {
        this.view = view;
        this.context=context;
        this.dbReposatory= DbReposatory.getInstance(LocalDbDataSource.getInstance(context));
    }

    @Override
    public Flowable<List<MealDb>> getLocalFavorits(String userName) {
       return dbReposatory.getFavorits(userName);
    }

    @Override
    public Completable deleteMealFromDb(String userName, String idMeal) {
       return dbReposatory.deleteMealFromDb(userName,idMeal);
    }
}
