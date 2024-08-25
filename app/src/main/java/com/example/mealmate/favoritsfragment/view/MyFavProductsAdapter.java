package com.example.mealmate.favoritsfragment.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mealmate.R;
import com.example.mealmate.homefragment.view.HomeFragmentRecycleAdapter;
import com.example.mealmate.model.MealDb;

import java.util.ArrayList;
import java.util.List;

public class MyFavProductsAdapter extends RecyclerView.Adapter<MyFavProductsAdapter.ViewHolder> {
    List<MealDb> meal;
    OnFavClickListener cardListener;
    public MyFavProductsAdapter(List<MealDb> meal,OnFavClickListener cardListener) {
        this.meal = meal;
        this.cardListener=cardListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.favorit_recycle_row, parent, false);
        return new MyFavProductsAdapter.ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titlee.setText(meal.get(position).getStrMeal());
        byte[] imageBytes = meal.get(position).getImage();
        if (imageBytes != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            holder.imagee.setImageBitmap(bitmap);
        }
        holder.favoritView.setOnClickListener(v-> {
            cardListener.deleteMealFromDb(meal.get(position).getUserName(),meal.get(position).getIdMeal());
        });
        holder.rowCard.setOnClickListener(v->{
            cardListener.showMealDeatails(meal.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return meal.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagee,favoritView;
        public TextView titlee;
        public CardView rowCard;

        public ViewHolder(View itemView) {
            super(itemView);
            this.titlee = (TextView) itemView.findViewById(R.id.mealNameView);
            this.imagee = (ImageView) itemView.findViewById(R.id.mealImageView);
            this.rowCard = (CardView) itemView.findViewById(R.id.meal_recycle_row);
            this.favoritView=(ImageView) itemView.findViewById(R.id.favoritView);
        }
    }
}