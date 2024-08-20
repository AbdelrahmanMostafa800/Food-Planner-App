package com.example.mealmate.mealdetails.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealmate.R;
import com.example.mealmate.mealdetails.presenter.MealDetailsPresenterImp;
import com.example.mealmate.mealdetails.presenter.MealDetailsPresenterInterface;
import com.example.mealmate.model.Meal;
import com.example.mealmate.model.countriespojo.CountriesList;

public class MealDetailsActivity extends AppCompatActivity implements MealDetailsActivityView{
    RecyclerView recyclerView;
    String mealID;
    MealDetailsPresenterInterface presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);
        Intent intent = getIntent();
        mealID= intent.getStringExtra("idMeal");
        presenter=new MealDetailsPresenterImp(this);
        presenter.getMealDetails(mealID);

//        recyclerView = findViewById(R.id.ingrediantRecycleView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        CountriesList countryList=CountriesList.getInstance();
//        IngeridiantRecycleAdapter adapter = new IngeridiantRecycleAdapter(countryList.getcountries());
//        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showMealDetails(Meal meal) {

    }
}