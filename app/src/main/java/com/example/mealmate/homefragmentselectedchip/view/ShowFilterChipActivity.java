package com.example.mealmate.homefragmentselectedchip.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealmate.R;
import com.example.mealmate.homefragmentselectedchip.presenter.ShowFilterChippresenter;
import com.example.mealmate.homefragmentselectedchip.presenter.ShowFilterChippresenterInterface;
import com.example.mealmate.mealdetails.view.MealDetailsActivity;
import com.example.mealmate.model.filterbycategorypojo.Meal;

import java.util.ArrayList;

public class ShowFilterChipActivity extends AppCompatActivity implements com.example.mealmate.homefragmentselectedchip.view.ShowFilterChipActivityView {

    ShowFilterChippresenterInterface presenter;
    RecyclerView recycleView;
    String StrCategory;
    String query;
    TextView categoryName;
    ImageView arrowBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_filter_chip);

        Intent intent = getIntent();
        StrCategory = intent.getStringExtra("StrCategory");
        query = intent.getStringExtra("query");

        presenter=new ShowFilterChippresenter(this);
        presenter.getFilterByCategory(query,StrCategory);

        recycleView=findViewById(R.id.rectangleCategory);
        recycleView.setLayoutManager(new GridLayoutManager(this,2));
        categoryName=findViewById(R.id.categoryName);
        categoryName.setText(StrCategory);
        arrowBack=findViewById(R.id.arrow_back);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void showFilterByCategory(ArrayList<Meal> meals) {
        MealCardClickListener mealCardClickListener = new MealCardClickListener(this);
        ShowFilterChipAdapter adapter = new ShowFilterChipAdapter(meals,mealCardClickListener);
        recycleView.setAdapter(adapter);
    }

}