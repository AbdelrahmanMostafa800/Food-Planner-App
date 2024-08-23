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

public class MealTransfere {
    public static MealDb insertMealIntoDb(Meal meall, Context context){
        MealDb mealDb=new MealDb();
        mealDb.setUserName("ahmed");
        mealDb.setIdMeal(meall.getIdMeal());
        mealDb.setStrMeal(meall.getStrMeal());
        mealDb.setStrCategory(meall.getStrCategory());
        mealDb.setStrInstructions(meall.getStrInstructions());
        mealDb.setStrArea(meall.getStrArea());
        mealDb.setStrYoutube(meall.getStrYoutube());
        Log.d("TAG",meall.getStrIngredients().size()+"" );
        mealDb.setIngredients(meall.getStrIngredients());
        mealDb.seMeasures(meall.getStrMeasures());
        Log.d("TAG",meall.getStrMeasures().size()+"" );
        Glide.with(context)
                .asBitmap()
                .load(meall.getStrMealThumb())
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        resource.compress(Bitmap.CompressFormat.PNG, 100, bos);
                        byte[] imageBytes = bos.toByteArray();
                        mealDb.setImage(imageBytes);

                    }
                });
        return mealDb;
    }
}
