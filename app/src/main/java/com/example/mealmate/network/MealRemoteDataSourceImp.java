package com.example.mealmate.network;

import android.util.Log;

import com.example.mealmate.model.category.CategoryList;
import com.example.mealmate.model.meal.MealList;
import com.example.mealmate.model.filterbycategorypojo.CategoryByFilter;
import com.example.mealmate.model.ingrediantpojo.IngrediantList;

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
    public void getFilterByCategory(ShowFilterChipNetworkCallBack networkCallback,String query, String strCategory) {
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

    @Override
    public void getMealDetails(MealDetailsNetworkCallBack networkCallback, String idMeal) {
        Call<MealList> call =  apiInterface.getMealById(idMeal);
        call.enqueue(new Callback<MealList>() {
            @Override
            public void onResponse(Call<MealList> call, Response<MealList> response) {
                Log.i(TAG,"onResponse: "+response.raw() +response.body().getMeals());
                networkCallback.onSuccessResultOfgetMealDetails(response.body().getMeals().get(0));
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
    public void makeNetworkCallGetMealsByFirstLetter(SearchFragmentNetworkCallBack networkCallback, String chatMealFilter) {
        Call<MealList> call =  apiInterface.getMealsByFirstLetter(chatMealFilter);
        call.enqueue(new Callback<MealList>() {
            @Override
            public void onResponse(Call<MealList> call, Response<MealList> response) {
                Log.i(TAG,"onResponse: "+response.raw() +response.body().getMeals());
                networkCallback.onSuccessResultOfgetMealsByFirstLetter(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<MealList> call, Throwable throwable) {
                Log.i(TAG,"onFailure: "+throwable.getMessage());
                networkCallback.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }


}

