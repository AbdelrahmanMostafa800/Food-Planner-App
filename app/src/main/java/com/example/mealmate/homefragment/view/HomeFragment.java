package com.example.mealmate.homefragment.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mealmate.SplashScreenActivity;
import com.example.mealmate.bottomsheet.BottomSheetAdapter;
import com.example.mealmate.MealDayTransfere;
import com.example.mealmate.MealTransfere;
import com.example.mealmate.bottomsheet.OnDaySelectedListener;
import com.example.mealmate.R;
import com.example.mealmate.homefragment.presenter.HomeFragmentPresenter;
import com.example.mealmate.homefragment.presenter.HomeFragmentPresenterImp;
import com.example.mealmate.mealdetails.view.MealDetailsActivity;
import com.example.mealmate.model.DayMealDb;
import com.example.mealmate.model.MealDb;
import com.example.mealmate.model.category.Category;
import com.example.mealmate.model.meal.Meal;
import com.example.mealmate.model.countriespojo.CountriesList;
import com.example.mealmate.model.userrepo.UserReposatoryImp;
import com.example.mealmate.model.userrepo.UserReposatoryInterface;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeFragment extends Fragment implements HomeFragmentView {

    HomeFragmentPresenter hpresenter;
    ImageView mealImageView,favoritView,calenderView,loginOut;
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
        hpresenter=new HomeFragmentPresenterImp(this,getContext());
         mealName=view.findViewById(R.id.mealNameView);
        mealdesc=view.findViewById(R.id.mealdesc);
         recyclerView = view.findViewById(R.id.recycleView);
        favoritView=view.findViewById(R.id.favoritView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
         mealImageView=view.findViewById(R.id.mealImageView);
        calenderView=view.findViewById(R.id.calenderView);
        loginOut=view.findViewById(R.id.loginout);
        if(hpresenter.getUserStatus().equals("Guest")){
            loginOut.setVisibility(View.INVISIBLE);
        }else{
            loginOut.setVisibility(View.VISIBLE);
        }
        loginOut.setOnClickListener(v-> {
            if(hpresenter.loginOut()){
                Intent intent=new Intent(getContext(), SplashScreenActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(getContext(),"Can't logout", Toast.LENGTH_SHORT).show();
            }
        });

        if(Objects.equals(hpresenter.getUserStatus(), "UserSignedUp")||Objects.equals(hpresenter.getUserStatus(), "UserLogidIn")){
        hpresenter.retrieveMealsFromFirestore();
        hpresenter.retrieveDayMealsFromFirestore();
        }

        calenderView.setOnClickListener(v-> {
            View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_layout, null);
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
            bottomSheetDialog.setContentView(bottomSheetView);

            RecyclerView recyclerView = bottomSheetView.findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            String[] weekDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
            Log.d("homeday", " weekDays");
            BottomSheetAdapter adapter = new BottomSheetAdapter(weekDays, new OnDaySelectedListener(){
                @Override
                public void onDaySelected(String day) {
                    Toast.makeText(getContext(),day,Toast.LENGTH_SHORT).show();
                    MealDayTransfere.insertMealIntoDb(day,meall,getContext())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<DayMealDb>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    Log.d("homeday", day+" onSubscribe");
                                }

                                @Override
                                public void onNext(DayMealDb mealDb) {
                                    Log.d("homeday", day+" onNext");
                                    hpresenter.insertDayMeal(day,mealDb);
                                }

                                @Override
                                public void onError(Throwable e) {
                                   Toast.makeText(getContext(),"error",Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onComplete() {
                                    // observable has completed
                                }
                            });
                    bottomSheetDialog.dismiss();
                }
            });
            recyclerView.setAdapter(adapter);

            bottomSheetDialog.show();
            Log.d("homeday", " show");
        });

        favoritView.setOnClickListener(v-> {
            MealTransfere.insertMealIntoDb(meall,getContext())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealDb>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // do nothing
                    }

                    @Override
                    public void onNext(MealDb mealDb) {
                        // Image download is complete, you can now use the MealDb object
                        hpresenter.insertMeal(mealDb);

                        // Save the MealDb object to the database
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle error
                    }

                    @Override
                    public void onComplete() {
                        // observable has completed
                    }
                });
            });

        chipGroup = view.findViewById(R.id.chipGroup);

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

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
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