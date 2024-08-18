package com.example.mealmate.model.mealdatarepo;

import com.example.mealmate.network.MealRemoteDataSourceImp;
import com.example.mealmate.network.MealRemoteDataSourceInterface;
import com.example.mealmate.network.NetworkCallback;

import io.reactivex.Observable;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class DataReposatoryImp implements DataReposatoryInterface{
    MealRemoteDataSourceInterface mealdatasource;
    private static DataReposatoryImp instance=null;
    private DataReposatoryImp(){
        this.mealdatasource=  MealRemoteDataSourceImp.getInstance();
    }
    public static synchronized DataReposatoryImp getInstance() {
        if (instance == null) {
            instance = new DataReposatoryImp();
        }
        return instance;
    }
    public void getSingleMeal(NetworkCallback networkCallback){
        mealdatasource.makeNetworkCall(networkCallback);
    }
}
