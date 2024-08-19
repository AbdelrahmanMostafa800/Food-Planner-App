package com.example.mealmate.homefragment.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealmate.R;
import com.example.mealmate.model.Category;
import com.example.mealmate.model.countriespojo.Country;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentRecycleAdapter extends RecyclerView.Adapter<HomeFragmentRecycleAdapter.ViewHolder>{
    ArrayList<Category> category;
    ArrayList<Country> countries;
    ArrayList<com.example.mealmate.model.ingrediantpojo.Meal> ingrediant;
    String currentSelected;
    private int pageSize = 20; // adjust this value to control the number of items per page
    private int currentPage = 1;
    public HomeFragmentRecycleAdapter(ArrayList<Category> category,ArrayList<Country> countries,ArrayList<com.example.mealmate.model.ingrediantpojo.Meal> ingrediant) {
        this.category = category;
        this.countries=countries;
        this.ingrediant=ingrediant;
        currentSelected="";
    }


    @NonNull
    @Override
    public HomeFragmentRecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.recycle_row, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeFragmentRecycleAdapter.ViewHolder holder, int position) {
        switch (currentSelected){
            case "category":
                holder.titlee.setText(category.get(position).getStrCategory());
                Glide.with(holder.itemView.getContext())
                        .load(category.get(position).getStrCategoryThumb())
                        .into(holder.imagee);
                break;
            case "countries":
                holder.titlee.setText(countries.get(position).getStrArea());
                Glide.with(holder.itemView.getContext())
                        .load(countries.get(position).getstrContryThumb())
                        .into(holder.imagee);
                break;
            case "ingrediant":
                holder.titlee.setText(ingrediant.get(position).getStrIngredient());
                Glide.with(holder.itemView.getContext())
                        .load("https://www.themealdb.com/images/ingredients/"+ingrediant.get(position).getStrIngredient()+".png")
                        .into(holder.imagee);
                break;
        }

    }

    @Override
    public int getItemCount() {
        if(category!=null) {
            currentSelected="category";
            return category.size();
        }
        else if(countries!=null) {
            currentSelected="countries";
            return countries.size();
        }
        else if(ingrediant!=null) {
            currentSelected="ingrediant";
            return Math.min(ingrediant.size(), currentPage * pageSize);
        }
        return 0;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagee;
        public TextView titlee;

        public ViewHolder(View itemView) {
            super(itemView);
            this.titlee = (TextView) itemView.findViewById(R.id.mealNameView);
            this.imagee = (ImageView) itemView.findViewById(R.id.mealImageView);
        }
    }
}
