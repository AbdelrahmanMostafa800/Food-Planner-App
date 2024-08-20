package com.example.mealmate.network;

import android.util.Log;

import com.example.mealmate.model.CategoryList;
import com.example.mealmate.model.MealList;
import com.example.mealmate.model.filterbycategorypojo.CategoryByFilter;
import com.example.mealmate.model.ingrediantpojo.IngrediantList;

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
    public void makeNetworkCallSingleMeal(HomeNetworkCallback networkCallback){
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
    public void makeNetworkCallCategory(HomeNetworkCallback networkCallback) {
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

    @Override
    public void makeNetworkCallIngrediants(HomeNetworkCallback networkCallback) {
        Call<IngrediantList> call =  apiInterface.getAllIngrediant();
        call.enqueue(new Callback<IngrediantList>() {
            @Override
            public void onResponse(Call<IngrediantList> call, Response<IngrediantList> response) {
                networkCallback.onRequestIngrediantSuccessResult(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<IngrediantList> call, Throwable throwable) {
                Log.i("TAG","onFailure: "+throwable.getMessage());
                networkCallback.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

    @Override
    public void getFilterByCategory(ShowFilterChipNetworkCallBack networkCallback,String query, String strCategory) {
        Log.d("query", query);
        Call<CategoryByFilter> call=null;
        switch (query){
            case "c":
                call=  apiInterface.getFilterByCategory(strCategory);
                break;
            case "i":
                call=  apiInterface.getFilterByIngredient(strCategory);
                break;
            case "a":
                call=  apiInterface.getFilterByArea(strCategory);
                break;
        }

        call.enqueue(new Callback<CategoryByFilter>() {
            @Override
            public void onResponse(Call<CategoryByFilter> call, Response<CategoryByFilter> response) {
                Log.d("getFilterByCategory", "onResponse: "+response.body().getMeals().get(0).getStrMeal());
                networkCallback.onRequestgetFilterByCategory(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<CategoryByFilter> call, Throwable throwable) {
                Log.i("TAG","onFailure: "+throwable.getMessage());
                networkCallback.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }


}

