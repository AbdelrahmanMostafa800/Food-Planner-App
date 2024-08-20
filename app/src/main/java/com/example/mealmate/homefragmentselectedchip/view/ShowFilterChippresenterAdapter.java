package com.example.mealmate.homefragmentselectedchip.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealmate.R;
import com.example.mealmate.model.filterbycategorypojo.Meal;

import java.util.ArrayList;

public class ShowFilterChippresenterAdapter  extends RecyclerView.Adapter<ShowFilterChippresenterAdapter.ViewHolder> {
    ArrayList<Meal> meals;
    onMealCArdRecycleClicked onMealCArdRecycleClicked;

    public ShowFilterChippresenterAdapter(ArrayList<Meal> meals,ShowFilterChipActivity onMealCArdRecycleClicked){
        this.meals=meals;
        this.onMealCArdRecycleClicked=onMealCArdRecycleClicked;

    }
    @NonNull
    @Override
    public ShowFilterChippresenterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.meal_recycle_row, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowFilterChippresenterAdapter.ViewHolder holder, int position) {
        holder.mealName.setText(meals.get(position).getStrMeal());
        Glide.with(holder.mealImage.getContext())
                .load(meals.get(position).getStrMealThumb())
                .into(holder.mealImage);
        holder.rowCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMealCArdRecycleClicked.goMealDetailsPage(meals.get(position).getIdMeal().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mealName;
        public ImageView mealImage;
        public ImageView favorit;
        public ImageView calender;
        public FrameLayout rowCard;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mealName = (TextView) itemView.findViewById(R.id.mealNameView);
            this.mealImage = (ImageView) itemView.findViewById(R.id.mealImageView);
            this.favorit = (ImageView) itemView.findViewById(R.id.favoritView);
            this.calender = (ImageView) itemView.findViewById(R.id.calenderView);
            this.rowCard=(FrameLayout)itemView.findViewById(R.id.meal_recycle_row);
        }
}
}