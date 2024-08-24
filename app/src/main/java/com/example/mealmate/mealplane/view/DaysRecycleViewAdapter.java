package com.example.mealmate.mealplane.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealmate.R;
import com.google.android.material.chip.Chip;

import java.util.List;

public class DaysRecycleViewAdapter extends RecyclerView.Adapter<DaysRecycleViewAdapter.ViewHolder> {
    private final List<String> chipTitles;
    private int selectedPosition = RecyclerView.NO_POSITION;
    OnDayClickListener onDayClickListener;
    public DaysRecycleViewAdapter(List<String> chipTitles,OnDayClickListener onDayClickListener) {
        this.chipTitles=chipTitles;
        this.onDayClickListener=onDayClickListener;
    }


    @NonNull
    @Override
    public DaysRecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.days_row, parent, false);
        return new DaysRecycleViewAdapter.ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull DaysRecycleViewAdapter.ViewHolder holder, int position) {
        holder.chipView.setText(chipTitles.get(position));

        // Handle chip selection
        holder.chipView.setChecked(position == selectedPosition);
        holder.chipView.setOnClickListener(v -> {
//            // Update the selected position and notify the adapter to refresh the view
//            int previousPosition = selectedPosition;
//            selectedPosition = position;
//
//            notifyItemChanged(previousPosition);
//            notifyItemChanged(selectedPosition);
            Log.d("day", chipTitles.get(position));
            onDayClickListener.onDayClick(chipTitles.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return chipTitles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Chip chipView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.chipView = (Chip) itemView.findViewById(R.id.chip_day);
        }
    }
}