package com.example.mealmate.searchfragment.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mealmate.R;
import com.example.mealmate.homefragment.view.ChipGroupFilterOnClickListener;
import com.example.mealmate.homefragment.view.HomeFragment;
import com.example.mealmate.homefragment.view.HomeFragmentRecycleAdapter;
import com.example.mealmate.homefragmentselectedchip.view.MealCardClickListener;
import com.example.mealmate.homefragmentselectedchip.view.ShowFilterChipActivity;
import com.example.mealmate.homefragmentselectedchip.view.ShowFilterChipAdapter;
import com.example.mealmate.homefragmentselectedchip.view.onMealCArdRecycleClicked;
import com.example.mealmate.model.countriespojo.CountriesList;
import com.example.mealmate.model.meal.Meal;
import com.example.mealmate.searchfragment.presenter.SearchPresenterImp;
import com.example.mealmate.searchfragment.presenter.SearchPresenterInterface;
import com.google.android.material.chip.Chip;
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
    ShowFilterChipAdapter adapter;
    boolean chipIsSelected;
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
        chipGroup = view.findViewById(R.id.chipGroup);
        presenter=new SearchPresenterImp(this);

        for(int i=0;i<chipGroup.getChildCount();i++){
            Chip chip=(Chip)chipGroup.getChildAt(i);
            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        chipIsSelected= true;
                        int checkedId = chipGroup.getCheckedChipId();
                        if (checkedId == R.id.search_category) {
                            presenter.getCategories();
                        } else if (checkedId == R.id.search_countries) {
                            CountriesList countryList=CountriesList.getInstance();
                            List<String> f=countryList.getcountries().stream().map(country -> country.getStrArea()).collect(Collectors.toList());
                            showListInSpinner(f);
                        } else if (checkedId == R.id.search_ingrediants) {
                            presenter.getIngrediants();
                        }

                    }
                }
            });
        }

        searchResult.setLayoutManager(new GridLayoutManager(getContext(),2));
        MealCardClickListener mealCardClickListener = new MealCardClickListener(getContext());
         adapter = new ShowFilterChipAdapter(new ArrayList<>(), mealCardClickListener);
        searchResult.setAdapter(adapter);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(!s.isEmpty()&& !chipIsSelected){
                    String chatMealFilter=s.toLowerCase().charAt(0)+"";
                    Toast.makeText(getContext(), chatMealFilter, Toast.LENGTH_SHORT).show();
                   presenter.getMealsByFirstLetter(chatMealFilter);
                }
                return true;
            }
        });

    }

    @Override
    public void showMeals(ArrayList<Meal> meals) {
        List<com.example.mealmate.model.filterbycategorypojo.Meal> mealss = meals.stream()
                .map(meal -> new com.example.mealmate.model.filterbycategorypojo.Meal(meal.getStrMeal(),meal.getStrMealThumb(),meal.getIdMeal()))
                .filter(item -> item.getStrMeal().toLowerCase().contains(search.getQuery().toString()))
                .collect(Collectors.toList());
        adapter.updateList(mealss);
    }
    public void showListInSpinner(List<String> items){
        Toast.makeText(getContext(), items.get(0), Toast.LENGTH_SHORT).show();
        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(getContext(), R.layout.spinner_item, items);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                presenter.getFilterByCategory("a",selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle case where nothing is selected if needed
            }
        });
    }
}