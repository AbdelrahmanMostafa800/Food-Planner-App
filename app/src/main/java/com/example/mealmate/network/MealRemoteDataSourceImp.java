package com.example.mealmate.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.mealmate.model.category.CategoryList;
import com.example.mealmate.model.meal.MealList;
import com.example.mealmate.model.filterbycategorypojo.CategoryByFilter;
import com.example.mealmate.model.ingrediantpojo.IngrediantList;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    private Context context;
    public MealRemoteDataSourceImp(Context context){
        this.context=context;
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        apiInterface=retrofit.create(ApiInterface.class);
    }
    public static MealRemoteDataSourceImp getInstance(Context context){
        if(client==null){
            client=new MealRemoteDataSourceImp(context);
        }
        return client;
    }
    public Observable<MealList> makeNetworkCallSingleMeal(){
      // return apiInterface.getSingleMeal();
        return apiInterface.getSingleMeal()
                .retryWhen(throwable -> throwable.flatMap(error -> {
                    if (error instanceof IOException) {
                        // Check if the error is due to no internet connection
                        if (!isNetworkAvailable()) {
                            return Observable.error(new NoInternetException("No internet connection"));
                        }
                    }
                    return Observable.timer(2, TimeUnit.SECONDS); // retry after 2 seconds
                }));
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public class NoInternetException extends RuntimeException {
        public NoInternetException(String message) {
            super(message);
        }
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

