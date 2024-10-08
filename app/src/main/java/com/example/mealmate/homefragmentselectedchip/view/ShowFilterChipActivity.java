package com.example.mealmate.homefragmentselectedchip.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealmate.R;
import com.example.mealmate.bottomsheet.BottomSheetAdapter;
import com.example.mealmate.bottomsheet.OnDaySelectedListener;
import com.example.mealmate.homefragmentselectedchip.presenter.ShowFilterChippresenter;
import com.example.mealmate.homefragmentselectedchip.presenter.ShowFilterChippresenterInterface;
import com.example.mealmate.model.filterbycategorypojo.Meal;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class ShowFilterChipActivity extends AppCompatActivity implements com.example.mealmate.homefragmentselectedchip.view.ShowFilterChipActivityView,OnfavoritClicked {

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

        presenter=new ShowFilterChippresenter(this,this);
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
        ShowFilterChipAdapter adapter = new ShowFilterChipAdapter(meals,mealCardClickListener,this);
        recycleView.setAdapter(adapter);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void insertMealToFavorit(String mealId) {
        Log.d("select", "chip ");
        presenter.getMealById(mealId,this);
    }

    @Override
    public void insertMealTocalender(String mealId) {
        Log.d("cal", mealId);
        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_layout, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(bottomSheetView);

        RecyclerView recyclerView = bottomSheetView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String[] weekDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        BottomSheetAdapter adapter = new BottomSheetAdapter(weekDays, new OnDaySelectedListener(){
            @Override
            public void onDaySelected(String day) {
                Log.d("cal", day);
                presenter.insertMealTocalender(day,mealId,ShowFilterChipActivity.this);
                bottomSheetDialog.dismiss();
            }
        });
        recyclerView.setAdapter(adapter);

        bottomSheetDialog.show();

    }
}