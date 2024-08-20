package com.example.mealmate.mealdetails.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealmate.R;
import com.example.mealmate.model.countriespojo.Country;

import java.util.ArrayList;

public class IngeridiantRecycleAdapter extends RecyclerView.Adapter<IngeridiantRecycleAdapter.ViewHolder>{
    ArrayList<Country> countries;
    public IngeridiantRecycleAdapter(ArrayList<Country> countries) {
        this.countries=countries;
    }


    @NonNull
    @Override
    public IngeridiantRecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.home_recycle_row, parent, false);
        return new IngeridiantRecycleAdapter.ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull IngeridiantRecycleAdapter.ViewHolder holder, int position) {
        holder.details.setText("dasdsdasdasdsadasd");
        holder.grames.setText("50 g");
        Glide.with(holder.itemView.getContext())
                .load(countries.get(position).getstrContryThumb())
                .into(holder.imagee);

    }

    @Override
    public int getItemCount() {
        return countries.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagee;
        public TextView details;
        public TextView grames;

        public ViewHolder(View itemView) {
            super(itemView);
            this.details = (TextView) itemView.findViewById(R.id.mealSDetails);
            this.imagee = (ImageView) itemView.findViewById(R.id.mealImg);
            this.grames = (TextView) itemView.findViewById(R.id.mealGrame);
        }
        }
    }

