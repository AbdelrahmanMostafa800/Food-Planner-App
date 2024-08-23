package com.example.mealmate.homefragmentselectedchip.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealmate.R;
import com.example.mealmate.model.filterbycategorypojo.Meal;

import java.util.ArrayList;
import java.util.List;

public class ShowFilterChipAdapter extends RecyclerView.Adapter<ShowFilterChipAdapter.ViewHolder> {
    ArrayList<Meal> meals;
    onMealCArdRecycleClicked onMealCArdRecycleClicked;
    OnfavoritClicked onfavoritClicked;

    public ShowFilterChipAdapter(ArrayList<Meal> meals, onMealCArdRecycleClicked onMealCArdRecycleClicked,OnfavoritClicked onfavoritClicked){
        if(meals!=null&&!meals.isEmpty()) {
            this.meals = meals;
        }else{
            this.meals=new ArrayList<>();
        }
        this.onMealCArdRecycleClicked=onMealCArdRecycleClicked;
        this.onfavoritClicked=onfavoritClicked;
    }
    @NonNull
    @Override
    public ShowFilterChipAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.meal_recycle_row, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowFilterChipAdapter.ViewHolder holder, int position) {
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
        holder.favorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("select", "adapter ");
                onfavoritClicked.insertMealToFavorit(meals.get(position).getIdMeal().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
    public void updateList(List<Meal> list) {
        this.meals = new ArrayList<>(list);
        notifyDataSetChanged();
    }

    public Iterable<? extends Meal> getList() {
        return meals;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mealName;
        public ImageView mealImage;
        public ImageView favorit;
        public ImageView calender;
        public CardView rowCard;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mealName = (TextView) itemView.findViewById(R.id.mealNameView);
            this.mealImage = (ImageView) itemView.findViewById(R.id.mealImageView);
            this.favorit = (ImageView) itemView.findViewById(R.id.favoritView);
            this.calender = (ImageView) itemView.findViewById(R.id.calenderView);
            this.rowCard=(CardView)itemView.findViewById(R.id.meal_recycle_row);
        }
}
}