package com.example.mealmate.mealplane.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealmate.R;
import com.example.mealmate.favoritsfragment.view.OnFavClickListener;
import com.example.mealmate.model.DayMealDb;
import com.example.mealmate.model.MealDb;

import java.util.List;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mealmate.R;
import com.example.mealmate.homefragment.view.HomeFragmentRecycleAdapter;
import com.example.mealmate.model.MealDb;

import java.util.ArrayList;
import java.util.List;

public class MealPlaneAdapter extends RecyclerView.Adapter<MealPlaneAdapter.ViewHolder> {
    List<DayMealDb> meal;
    public MealPlaneAdapter(List<DayMealDb> meal) {
        this.meal = meal;
    }

    @NonNull
    @Override
    public MealPlaneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.meal_plane_row, parent, false);
        return new MealPlaneAdapter.ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MealPlaneAdapter.ViewHolder holder, int position) {
        holder.titlee.setText(meal.get(position).getStrMeal());
        byte[] imageBytes = meal.get(position).getImage();
        if (imageBytes != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            holder.imagee.setImageBitmap(bitmap);
        }
//        holder.favoritView.setOnClickListener(v-> {
//            cardListener.deleteMealFromDb("ahmed",meal.get(position).getIdMeal());
//        });

    }

    @Override
    public int getItemCount() {
        return meal.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagee;
        public TextView titlee;
        public FrameLayout rowCard;

        public ViewHolder(View itemView) {
            super(itemView);
            this.titlee = (TextView) itemView.findViewById(R.id.mealNameView);
            this.imagee = (ImageView) itemView.findViewById(R.id.mealImageView);
            this.rowCard = (FrameLayout) itemView.findViewById(R.id.meal_recycle_row);
        }
    }
}
