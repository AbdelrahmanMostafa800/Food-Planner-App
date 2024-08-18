package com.example.mealmate.model.mealdatarepo;

import com.example.mealmate.network.MealRemoteDataSourceImp;
import com.example.mealmate.network.MealRemoteDataSourceInterface;
import com.example.mealmate.network.NetworkCallback;

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
        mealdatasource.makeNetworkCallSingleMeal(networkCallback);
    }

    @Override
    public void getCategories(NetworkCallback networkCallback) {
        mealdatasource.makeNetworkCallCategory(networkCallback);
    }
}
