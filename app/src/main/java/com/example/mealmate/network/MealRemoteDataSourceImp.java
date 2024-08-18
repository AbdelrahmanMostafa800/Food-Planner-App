package com.example.mealmate.network;

import android.util.Log;

import com.example.mealmate.model.CategoryList;
import com.example.mealmate.model.Meal;
import com.example.mealmate.model.MealList;

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
                .build();
        apiInterface=retrofit.create(ApiInterface.class);
    }
    public static MealRemoteDataSourceImp getInstance(){
        if(client==null){
            client=new MealRemoteDataSourceImp();
        }
        return client;
    }
    public void makeNetworkCallSingleMeal(NetworkCallback networkCallback){
        Call<MealList> call =  apiInterface.getSingleMeal();
        call.enqueue(new Callback<MealList>() {
            @Override
            public void onResponse(Call<MealList> call, Response<MealList> response) {
                Log.i(TAG,"onResponse: "+response.raw() +response.body().getMeals());
                networkCallback.onSuccessResult(response.body().getMeals().get(0));
            }

            @Override
            public void onFailure(Call<MealList> call, Throwable throwable) {
                Log.i(TAG,"onFailure: "+throwable.getMessage());
                networkCallback.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

    @Override
    public void makeNetworkCallCategory(NetworkCallback networkCallback) {
        Call<CategoryList> call =  apiInterface.getAllCategories();
        call.enqueue(new Callback<CategoryList>() {
            @Override
            public void onResponse(Call<CategoryList> call, Response<CategoryList> response) {
                Log.i("TAG","onResponse: "+response.raw() +response.body().getCategories());
                Log.d("categories1", "onCheckedChanged: "+response.body().getCategories().toString());
                networkCallback.onRequestCategorySuccessResult(response.body().getCategories());
            }

            @Override
            public void onFailure(Call<CategoryList> call, Throwable throwable) {
                Log.i("TAG","onFailure: "+throwable.getMessage());
                networkCallback.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
}
