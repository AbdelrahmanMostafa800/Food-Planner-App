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

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentRecycleAdapter extends RecyclerView.Adapter<HomeFragmentRecycleAdapter.ViewHolder>{
    ArrayList<Category> category;
    Context context;
    public HomeFragmentRecycleAdapter(ArrayList<Category> category) {
        this.category = category;
        this.context = context;
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
        holder.titlee.setText(category.get(position).getStrCategory());
        Glide.with(holder.itemView.getContext())
                .load(category.get(position).getStrCategoryThumb())
                .into(holder.imagee);
    }

    @Override
    public int getItemCount() {
        return category!=null?category.size():0;
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
