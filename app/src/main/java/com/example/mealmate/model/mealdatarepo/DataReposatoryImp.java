package com.example.mealmate.model.mealdatarepo;

import com.example.mealmate.mealdetails.presenter.MealDetailsPresenterImp;
import com.example.mealmate.model.category.CategoryList;
import com.example.mealmate.model.ingrediantpojo.IngrediantList;
import com.example.mealmate.model.meal.MealList;
import com.example.mealmate.network.MealRemoteDataSourceImp;
import com.example.mealmate.network.MealRemoteDataSourceInterface;
import com.example.mealmate.network.HomeNetworkCallback;
import com.example.mealmate.network.SearchFragmentNetworkCallBack;

import io.reactivex.rxjava3.core.Observable;

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
    public Observable<MealList> getSingleMeal(){
       return mealdatasource.makeNetworkCallSingleMeal();
    }

    @Override
    public Observable<CategoryList> getCategories() {
       return mealdatasource.makeNetworkCallCategory();
    }

    @Override
    public Observable<IngrediantList> getIngrediants() {
       return mealdatasource.makeNetworkCallIngrediants();
    }

    @Override
    public void getMealDetails(MealDetailsPresenterImp mealDetailsPresenterImp, String idMeal) {
        mealdatasource.getMealDetails(mealDetailsPresenterImp,idMeal);
    }

    @Override
    public void getMealsByFirstLetter(SearchFragmentNetworkCallBack networkCallback, String chatMealFilter) {
        mealdatasource.makeNetworkCallGetMealsByFirstLetter(networkCallback,chatMealFilter);
    }

}
