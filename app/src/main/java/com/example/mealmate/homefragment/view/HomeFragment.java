package com.example.mealmate.homefragment.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.mealmate.R;
import com.example.mealmate.homefragment.presenter.HomeFragmentPresenter;
import com.example.mealmate.homefragment.presenter.HomeFragmentPresenterImp;
import com.example.mealmate.homefragmentselectedchip.view.ShowFilterChipActivity;
import com.example.mealmate.mealdetails.view.MealDetailsActivity;
import com.example.mealmate.model.MealDb;
import com.example.mealmate.model.category.Category;
import com.example.mealmate.model.meal.Meal;
import com.example.mealmate.model.countriespojo.CountriesList;
import com.example.mealmate.model.userrepo.UserReposatoryImp;
import com.example.mealmate.model.userrepo.UserReposatoryInterface;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class HomeFragment extends Fragment implements HomeFragmentView{

    HomeFragmentPresenter hpresenter;
    ImageView mealImageView,favoritView;
    TextView mealName;
    RecyclerView recyclerView;
    CardView mealdesc;
    ChipGroup chipGroup;
    Meal meall;
    ChipGroupFilterOnClickListener chipGroupFilterOnClickListener;

    public HomeFragment() {
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UserReposatoryInterface reposatory= UserReposatoryImp.getInstance(getContext());

        TextView nameText=view.findViewById(R.id.nameText);
         mealName=view.findViewById(R.id.mealNameView);
        mealdesc=view.findViewById(R.id.mealdesc);
         recyclerView = view.findViewById(R.id.recycleView);
        favoritView=view.findViewById(R.id.favoritView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
         mealImageView=view.findViewById(R.id.mealImageView);
        nameText.setText(getString(R.string.hellow)+reposatory.getUserLocalData()[1]+"!");

        favoritView.setOnClickListener(v-> {
            MealDb mealDb=new MealDb();
            mealDb.setUserName("ahmed");
            mealDb.setIdMeal(meall.getIdMeal());
            mealDb.setStrMeal(meall.getStrMeal());
            mealDb.setStrCategory(meall.getStrCategory());
            mealDb.setStrInstructions(meall.getStrInstructions());
            mealDb.setStrArea(meall.getStrArea());
            mealDb.setStrYoutube(meall.getStrYoutube());
            Log.d("TAG",meall.getStrIngredients().size()+"" );
            mealDb.setIngredients(meall.getStrIngredients());
            mealDb.seMeasures(meall.getStrMeasures());
            Log.d("TAG",meall.getStrMeasures().size()+"" );
            Glide.with(getContext())
                    .asBitmap()
                    .load(meall.getStrMealThumb())
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                            ByteArrayOutputStream bos = new ByteArrayOutputStream();
                            resource.compress(Bitmap.CompressFormat.PNG, 100, bos);
                            byte[] imageBytes = bos.toByteArray();
                            mealDb.setImage(imageBytes);
                            hpresenter.insertMeal(mealDb);
                        }
                    });
            });

        chipGroup = view.findViewById(R.id.chipGroup);
        hpresenter=new HomeFragmentPresenterImp(this,getContext());
        //api
        hpresenter.getSingleMeal();
        //api
        hpresenter.getCategories();

         chipGroupFilterOnClickListener = new ChipGroupFilterOnClickListener(getContext());
        for(int i=0;i<chipGroup.getChildCount();i++){
            Chip chip=(Chip)chipGroup.getChildAt(i);
            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        int checkedId = chipGroup.getCheckedChipId();
                        if (checkedId == R.id.chip_category) {
                            //api
                           hpresenter.getCategories();
                        } else if (checkedId == R.id.chip_countries) {
                            CountriesList countryList=CountriesList.getInstance();
                            HomeFragmentRecycleAdapter adapter = new HomeFragmentRecycleAdapter(null,countryList.getcountries(),null,chipGroupFilterOnClickListener);
                            recyclerView.setAdapter(adapter);
                        } else if (checkedId == R.id.chip_ingrediants) {
                            //api
                            hpresenter.getIngrediants();
                        }

                    }
                }
            });
        }
    }

    @Override
    public void showMeal(Meal meal) {
        meall=meal;
        mealName.setText(meal.getStrMeal());
        Glide.with(mealImageView.getContext())
                .load(meal.getStrMealThumb())
                .into(mealImageView);
        mealdesc.setOnClickListener(view-> {
                Intent intent = new Intent(getActivity(), MealDetailsActivity.class);
                intent.putExtra("idMeal", meal.getIdMeal());
                startActivity(intent);

        });
    }

    @Override
    public void showCategories(ArrayList<Category> category) {
        HomeFragmentRecycleAdapter adapter = new HomeFragmentRecycleAdapter(category,null,null,chipGroupFilterOnClickListener);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void showIngrediants(ArrayList<com.example.mealmate.model.ingrediantpojo.Meal> meals) {
        HomeFragmentRecycleAdapter adapter = new HomeFragmentRecycleAdapter(null,null,meals,chipGroupFilterOnClickListener);
        recyclerView.setAdapter(adapter);
    }


}

/*
Bitmap bitmap = ...; // Load the image bitmap
ByteArrayOutputStream bos = new ByteArrayOutputStream();
bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
byte[] imageBytes = bos.toByteArray();

Meal meal = new Meal();
meal.setImage(imageBytes);
// Save the meal to the database
Meal meal = ...; // Retrieve the meal from the database
byte[] imageBytes = meal.getImage();
Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
* */