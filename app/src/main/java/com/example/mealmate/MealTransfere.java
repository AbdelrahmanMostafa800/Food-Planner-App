package com.example.mealmate;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.mealmate.model.MealDb;
import com.example.mealmate.model.meal.Meal;

import java.io.ByteArrayOutputStream;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealTransfere {
    public static Observable<MealDb> insertMealIntoDb(Meal meall, Context context){
        return Observable.create(emitter -> {
            MealDb mealDb=new MealDb();
            mealDb.setUserName("ahmed");
            mealDb.setIdMeal(meall.getIdMeal());
            mealDb.setStrMeal(meall.getStrMeal());
            mealDb.setStrCategory(meall.getStrCategory());
            mealDb.setStrInstructions(meall.getStrInstructions());
            mealDb.setStrArea(meall.getStrArea());
            mealDb.setStrYoutube(meall.getStrYoutube());
            mealDb.setIngredients(meall.getStrIngredients());
            mealDb.seMeasures(meall.getStrMeasures());

            Glide.with(context)
                    .asBitmap()
                    .load(meall.getStrMealThumb())
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byte[] imageBytes = stream.toByteArray();
                            mealDb.setImage(imageBytes);
                            emitter.onNext(mealDb);
                            emitter.onComplete();
                        }
                    });
        });
//        MealDb meallDb=null;
//        getMealDb(meall, context)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<MealDb>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        // do nothing
//                    }
//
//                    @Override
//                    public void onNext(MealDb mealDb) {
//                        // Image download is complete, you can now use the MealDb object
//                        Log.d("MealDb", mealDb.toString());
//
//                        // Save the MealDb object to the database
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        // handle error
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        // observable has completed
//                    }
//                });
//        MealDb mealDb=new MealDb();
//        mealDb.setUserName("ahmed");
//        mealDb.setIdMeal(meall.getIdMeal());
//        mealDb.setStrMeal(meall.getStrMeal());
//        mealDb.setStrCategory(meall.getStrCategory());
//        mealDb.setStrInstructions(meall.getStrInstructions());
//        mealDb.setStrArea(meall.getStrArea());
//        mealDb.setStrYoutube(meall.getStrYoutube());
//        mealDb.setIngredients(meall.getStrIngredients());
//        mealDb.seMeasures(meall.getStrMeasures());
//        Log.d("transfer", meall.getStrMealThumb());
//        Glide.with(context)
//                .asBitmap()
//                .load(meall.getStrMealThumb())
//                .into(new SimpleTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
//                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                        resource.compress(Bitmap.CompressFormat.PNG, 100, bos);
//                        byte[] imageBytes = bos.toByteArray();
//                        mealDb.setImage(imageBytes);
//
//                    }
//                });
//        Log.d("transfer", mealDb.getImage().toString());
//        return mealDb;
    }
//    public static Observable<MealDb> getMealDb(Meal meall, Context context) {
//        return Observable.create(emitter -> {
//            MealDb mealDb=new MealDb();
//            mealDb.setUserName("ahmed");
//            mealDb.setIdMeal(meall.getIdMeal());
//            mealDb.setStrMeal(meall.getStrMeal());
//            mealDb.setStrCategory(meall.getStrCategory());
//            mealDb.setStrInstructions(meall.getStrInstructions());
//            mealDb.setStrArea(meall.getStrArea());
//            mealDb.setStrYoutube(meall.getStrYoutube());
//            mealDb.setIngredients(meall.getStrIngredients());
//            mealDb.seMeasures(meall.getStrMeasures());
//
//            Glide.with(context)
//                    .asBitmap()
//                    .load(meall.getStrMealThumb())
//                    .into(new SimpleTarget<Bitmap>() {
//                        @Override
//                        public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
//                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                            byte[] imageBytes = stream.toByteArray();
//                            mealDb.setImage(imageBytes);
//                            emitter.onNext(mealDb);
//                            emitter.onComplete();
//                        }
//                    });
//        });
//    }
}

