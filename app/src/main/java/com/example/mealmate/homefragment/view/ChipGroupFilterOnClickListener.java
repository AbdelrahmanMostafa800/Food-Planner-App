package com.example.mealmate.homefragment.view;

import android.content.Context;
import android.content.Intent;

import com.example.mealmate.homefragmentselectedchip.view.ShowFilterChipActivity;
import com.example.mealmate.mealdetails.view.MealDetailsActivity;

public class ChipGroupFilterOnClickListener implements OnCardClickListener{
    private Context context;

    public ChipGroupFilterOnClickListener(Context context) {
        this.context = context;
    }


    @Override
    public void goShowFilterChipPage(String query, String StrCategory) {
        Intent intent = new Intent(context, ShowFilterChipActivity.class);
        intent.putExtra("StrCategory", StrCategory);
        intent.putExtra("query", query);
        context.startActivity(intent);
    }
}
