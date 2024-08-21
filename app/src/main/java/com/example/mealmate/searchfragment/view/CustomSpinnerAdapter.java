package com.example.mealmate.searchfragment.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mealmate.R;

import java.util.List;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {
    private Context context;
    private List<String> items;

    public CustomSpinnerAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.spinner_item, parent, false);
        }
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText(items.get(position));
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.spinner_dropdown_item, parent, false);
        }
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText(items.get(position));
        return view;
    }
}
