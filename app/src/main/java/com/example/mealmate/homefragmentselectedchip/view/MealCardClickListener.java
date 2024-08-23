package com.example.mealmate.homefragmentselectedchip.view;

import android.content.Context;
import android.content.Intent;

import com.example.mealmate.MealTransfere;
import com.example.mealmate.mealdetails.view.MealDetailsActivity;

public class MealCardClickListener implements onMealCArdRecycleClicked{
    private Context context;

    public MealCardClickListener(Context context) {
        this.context = context;
    }

    @Override
    public void goMealDetailsPage(String mealId) {
        Intent intent = new Intent(context, MealDetailsActivity.class);
        intent.putExtra("idMeal", mealId);
        context.startActivity(intent);
    }

}
