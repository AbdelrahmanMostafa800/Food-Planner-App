package com.example.mealmate.searchfragment.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mealmate.R;
import com.example.mealmate.model.meal.Meal;
import com.example.mealmate.searchfragment.presenter.SearchPresenterImp;
import com.example.mealmate.searchfragment.presenter.SearchPresenterInterface;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchFragment extends Fragment implements SearchFragmentView{

    ImageView arrowBack;
    SearchView search;
    ChipGroup chipGroup;
    Spinner spinner;
    RecyclerView searchResult;
    SearchPresenterInterface presenter;
    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        arrowBack=view.findViewById(R.id.searchfragmentarrow_back);
        search=view.findViewById(R.id.searchView);
        chipGroup=view.findViewById(R.id.chipGroup);
        spinner=view.findViewById(R.id.searchselectspinner);
        searchResult=view.findViewById(R.id.searchresult);
        presenter=new SearchPresenterImp(this);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(!s.isEmpty()){
                    String chatMealFilter=s.toLowerCase().charAt(0)+"";
                   presenter.getMealsByFirstLetter(chatMealFilter);
                }
                return true;
            }
        });

        List<String> items = new ArrayList<>();
        items.add("Option 1");
        items.add("Option 2");
        items.add("Option 3");
        items.add("Option 4");
        items.add("Option 1");
        items.add("Option 2");
        items.add("Option 3");
        items.add("Option 4");
        items.add("Option 1");
        items.add("Option 2");
        items.add("Option 3");
        items.add("Option 4");
        items.add("Option 1");
        items.add("Option 2");
        items.add("Option 3");
        items.add("Option 4");
        items.add("Option 1");
        items.add("Option 2");
        items.add("Option 3");
        items.add("Option 4");

        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(getContext(), R.layout.spinner_item, items);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                Toast.makeText(getContext(), "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle case where nothing is selected if needed
            }
        });
    }

    @Override
    public void showMeals(ArrayList<Meal> meals) {
        Toast.makeText(getContext(), meals.get(0).getStrMeal(), Toast.LENGTH_SHORT).show();
    }
}