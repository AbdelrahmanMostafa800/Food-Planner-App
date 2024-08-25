package com.example.mealmate.favoritsfragment.presenter;

import android.content.Context;

import com.example.mealmate.db.localdb.LocalDbDataSource;
import com.example.mealmate.favoritsfragment.view.FavoritsFragmentView;
import com.example.mealmate.model.MealDb;
import com.example.mealmate.model.dbreposatory.DbReposatory;
import com.example.mealmate.model.dbreposatory.DbReposatoryInterface;
import com.example.mealmate.model.userrepo.UserAuthReposatoryImp;
import com.example.mealmate.model.userrepo.UserAuthReposatoryInterface;
import com.example.mealmate.model.userrepo.UserReposatoryImp;
import com.example.mealmate.model.userrepo.UserReposatoryInterface;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;


public class FavoritsPresenter implements FavoritsPresenterInterface{
    FavoritsFragmentView view;
    DbReposatoryInterface dbReposatory;
    UserAuthReposatoryInterface userAuthReposatory;
    Context context;
    UserReposatoryInterface userRepo;;
    public FavoritsPresenter(FavoritsFragmentView view, Context context) {
        this.view = view;
        this.context=context;
        this.dbReposatory= DbReposatory.getInstance(LocalDbDataSource.getInstance(context));
        this.userAuthReposatory= UserAuthReposatoryImp.getInstance();
        userRepo= UserReposatoryImp.getInstance(context);
    }

    @Override
    public Flowable<List<MealDb>> getLocalFavorits(String userName) {
       return dbReposatory.getFavorits(userName);
    }

    @Override
    public Completable deleteMealFromDb(String userName, String idMeal) {
       return dbReposatory.deleteMealFromDb(userName,idMeal);
    }

    @Override
    public Completable saveToFirebaseDb(List<MealDb> mealDbsList) {
       return dbReposatory.saveToFirebaseDb(mealDbsList);
    }

    @Override
    public String getUserId() {
        return userAuthReposatory.getUserId();
    }

    @Override
    public String getUserStatus() {
        return userRepo.getUserLoginStatus();
    }
}
