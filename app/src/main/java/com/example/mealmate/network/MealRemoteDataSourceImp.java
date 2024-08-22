package com.example.mealmate.network;

import android.util.Log;

import com.example.mealmate.model.category.CategoryList;
import com.example.mealmate.model.meal.MealList;
import com.example.mealmate.model.filterbycategorypojo.CategoryByFilter;
import com.example.mealmate.model.ingrediantpojo.IngrediantList;

import java.util.HashMap;
import java.util.Map;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDataSourceImp implements MealRemoteDataSourceInterface{
    public static final String TAG="AllProductsActivity";
    private static final String BASE_URL="https://themealdb.com/";
    private ApiInterface apiInterface;
    private static MealRemoteDataSourceImp client =null;
    public MealRemoteDataSourceImp(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        apiInterface=retrofit.create(ApiInterface.class);
    }
    public static MealRemoteDataSourceImp getInstance(){
        if(client==null){
            client=new MealRemoteDataSourceImp();
        }
        return client;
    }
    public Observable<MealList> makeNetworkCallSingleMeal(){
       return apiInterface.getSingleMeal();
    }

    @Override
    public Observable<CategoryList> makeNetworkCallCategory() {
         return apiInterface.getAllCategories();
    }

    @Override
    public Observable<IngrediantList> makeNetworkCallIngrediants() {
        return apiInterface.getAllIngrediant();
    }

    @Override
    public Observable<CategoryByFilter> getFilterByCategory(String query, String strCategory) {
        Map<String, String> params = new HashMap<>();
        params.put(query, strCategory);
        return apiInterface.getFilterByParams(params);
    }

    @Override
    public Observable<MealList> getMealDetails(String idMeal) {
       return apiInterface.getMealById(idMeal);
    }

    @Override
    public Observable<MealList>  makeNetworkCallGetMealsByFirstLetter(String chatMealFilter) {
       return apiInterface.getMealsByFirstLetter(chatMealFilter);
    }


}

